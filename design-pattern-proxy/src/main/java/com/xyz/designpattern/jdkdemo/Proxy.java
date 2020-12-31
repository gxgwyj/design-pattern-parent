package com.xyz.designpattern.jdkdemo;

import sun.misc.ProxyGenerator;
import sun.misc.VM;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;
import sun.security.util.SecurityConstants;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;

/**
 * @author gaoxugang
 * @data 2020/12/13  14:17
 * @description jdk Proxy demo
 */
public class Proxy implements java.io.Serializable{

    private static final long serialVersionUID = -4778784062797265715L;

    /**
     * 生成代理类构造参数
     */
    private static final Class<?>[] constructorParams = {InvocationHandler.class};

    /**
     * 存储生成代理类的缓存
     */
    private static final WeakCache<ClassLoader, Class<?>[], Class<?>> proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());

    /**
     * 调用处理程序
     */
    protected InvocationHandler h;

    private Proxy() {
    }

    protected Proxy(InvocationHandler h) {
        Objects.requireNonNull(h);
        this.h = h;
    }

    /**
     * 获取代理类
     *
     * @param loader
     * @param interfaces
     * @return
     * @throws IllegalArgumentException
     */
    public static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces) throws IllegalArgumentException {
        final Class<?>[] intfs = interfaces.clone();
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        return getProxyClass0(loader, intfs);
    }

    private static void checkProxyAccess(Class<?> callerClass, ClassLoader loader, Class<?>[] interfaces) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            ClassLoader ccl = callerClass.getClassLoader();
            if (VM.isSystemDomainLoader(loader) && !VM.isSystemDomainLoader(ccl)) {
                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
            }
            ReflectUtil.checkProxyPackageAccess(ccl, interfaces);
        }
    }

    /**
     * 获取代理类
     * @param loader 类加载器
     * @param interfaces 接口数组
     * @return
     */
    private static Class<?> getProxyClass0(ClassLoader loader, Class<?>[] interfaces) {
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }
        // 先从缓存中取，缓存中没有创建
        return proxyClassCache.get(loader, interfaces);
    }

    /**
     * 实现0个接口的代理缓存key
     */
    private static final Object key0 = new Object();


    /**
     * 实现1个接口的代理缓存key
     */
    private static final class Key1 extends WeakReference<Class<?>> {
        private final int hash;

        Key1(Class<?> intf) {
            super(intf);
            this.hash = intf.hashCode();
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            Class<?> intf;
            return this == obj ||
                    obj != null &&
                            obj.getClass() == Key1.class &&
                            (intf = get()) != null &&
                            intf == ((Key1) obj).get();
        }
    }


    /**
     * 实现2个接口的代理类缓存的key
     */
    private static final class Key2 extends WeakReference<Class<?>> {
        private final int hash;
        private final WeakReference<Class<?>> ref2;
        /**
         * @param intf1 接口1
         * @param intf2 接口2
         */
        Key2(Class<?> intf1, Class<?> intf2) {
            super(intf1);
            hash = 31 * intf1.hashCode() + intf2.hashCode();
            ref2 = new WeakReference<Class<?>>(intf2);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            Class<?> intf1, intf2;
            return this == obj ||
                    obj != null &&
                            obj.getClass() == Key2.class &&
                            (intf1 = get()) != null &&
                            intf1 == ((Key2) obj).get() &&
                            (intf2 = ref2.get()) != null &&
                            intf2 == ((Key2) obj).ref2.get();
        }
    }

    /**
     * 实现3个及以上接口的代理类缓存的key
     */
    private static final class KeyX {
        private final int hash;
        private final WeakReference<Class<?>>[] refs;

        @SuppressWarnings("unchecked")
        KeyX(Class<?>[] interfaces) {
            hash = Arrays.hashCode(interfaces);
            refs = (WeakReference<Class<?>>[])new WeakReference<?>[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                refs[i] = new WeakReference<>(interfaces[i]);
            }
        }

        @Override
        public int hashCode() {
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj ||
                    obj != null &&
                            obj.getClass() == KeyX.class &&
                            equals(refs, ((KeyX) obj).refs);
        }

        private static boolean equals(WeakReference<Class<?>>[] refs1,
                                      WeakReference<Class<?>>[] refs2) {
            if (refs1.length != refs2.length) {
                return false;
            }
            for (int i = 0; i < refs1.length; i++) {
                Class<?> intf = refs1[i].get();
                if (intf == null || intf != refs2[i].get()) {
                    return false;
                }
            }
            return true;
        }
    }


    /**
     * 缓存key工厂
     */
    private static final class KeyFactory implements BiFunction<ClassLoader, Class<?>[], Object> {
        @Override
        public Object apply(ClassLoader o, Class<?>[] interfaces) {
            switch (interfaces.length) {
                case 1:
                    return new Key1(interfaces[0]);
                case 2:
                    return new Key2(interfaces[0], interfaces[1]);
                case 0:
                    return key0;
                default:
                        return new KeyX(interfaces);
            }
        }
    }



    /**
     * 代理类生成工厂，入参是classLoader和接口类型数组，返回值为一个类类型
     */
    private static final class ProxyClassFactory implements BiFunction<ClassLoader,Class<?>[],Class<?>> {

        // 生成代理类名称前缀
        private static final String proxyClassNamePrefix = "$Proxy";

        // 代理接口名称序列号
        private static final AtomicLong nextUniqueNumber = new AtomicLong();

        /**
         * @param classLoader 类加载器
         * @param interfaces 接口数组
         * @return  返回一个创建好的代理类
         */
        @Override
        public Class<?> apply(ClassLoader classLoader, Class<?>[] interfaces) {

            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);

            // 验证接口
            for (Class<?> intf: interfaces) {
                Class<?> interfaceClass = null;

                try {
                    interfaceClass = Class.forName(intf.getName(), false, classLoader);
                } catch (ClassNotFoundException e) {
                }

                if (intf != interfaceClass) {
                    throw new IllegalArgumentException(intf + " is not visible from class loader");
                }

                // 判断是否接口
                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(interfaceClass.getName() + " is not an interface");
                }

                // 验证是否重复
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException("repeated interface: " + interfaceClass.getName());
                }
            }

            // 代理类包名
            String proxyPkg = null;

            // 访问标识(public+final)
            int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

            for (Class<?> intf: interfaces) {
                int flags = intf.getModifiers();
                if (!Modifier.isPublic(flags)) {
                    accessFlags = Modifier.FINAL;
                    String name = intf.getName();
                    int n = name.lastIndexOf(".");
                    String pkg = (n == -1) ? "" : name.substring(0, n + 1);
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                                "non-public interfaces from different packages");
                    }
                }
            }

            if (proxyPkg == null) {
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }

            // 代理类名称
            long num = nextUniqueNumber.getAndIncrement();
            String proxyName = proxyPkg + proxyClassNamePrefix + num;

            // 创建字节文件
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(proxyName, interfaces, accessFlags);


            try {
                return defineClass0(classLoader, proxyName,
                        proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
                throw new IllegalArgumentException(e.toString());
            }
        }


        /**
         * 验证是Proxy的子类
         * @param cl
         * @return
         */
        public static boolean isProxyClass(Class<?> cl) {
            return Proxy.class.isAssignableFrom(cl) && proxyClassCache.containsValue(cl);
        }


        @CallerSensitive
        public static InvocationHandler getInvocationHandler(Object proxy) throws IllegalArgumentException {

            if (!isProxyClass(proxy.getClass())) {
                throw new IllegalArgumentException("not a proxy instance");
            }

            final Proxy p = (Proxy) proxy;
            final InvocationHandler ih = p.h;

            if (System.getSecurityManager() != null) {
                Class<?> ihClass = ih.getClass();
                Class<?> caller = Reflection.getCallerClass();

                if (ReflectUtil.needsPackageAccessCheck(caller.getClassLoader(), ihClass.getClassLoader())) {
                    ReflectUtil.checkPackageAccess(ihClass);
                }

            }
            return ih;
        }


        /**
         * 加载类 native方法
         * @param loader
         * @param name
         * @param b 文件
         * @param off
         * @param len
         * @return
         */
        private static native Class<?> defineClass0(ClassLoader loader, String name,
                                                    byte[] b, int off, int len);
    }


}
