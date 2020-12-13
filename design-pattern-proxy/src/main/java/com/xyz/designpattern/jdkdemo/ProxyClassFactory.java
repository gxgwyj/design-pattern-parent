package com.xyz.designpattern.jdkdemo;

import sun.misc.ProxyGenerator;
import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.Modifier;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;

/**
 * @author gaoxugang
 * @data 2020/11/28  21:34
 * @description （模拟java代理类）代理生成工厂类，输入参数:类加载器，接口数组，返回生成代理类
 */
public class ProxyClassFactory implements BiFunction<ClassLoader,Class<?>[],Class<?>> {

    // 生成代理类名称前缀
    private static final String proxyClassNamePrefix = "$Proxy";

    // 代理类唯一序列
    private static final AtomicLong nextUniqueNumber = new AtomicLong();

    /**
     * 入参
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
                interfaceClass = Class.forName(intf.getName(),false,classLoader);
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
                    proxyPkg = null;
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
            /*
             * A ClassFormatError here means that (barring bugs in the
             * proxy class generation code) there was some other
             * invalid aspect of the arguments supplied to the proxy
             * class creation (such as virtual machine limitations
             * exceeded).
             */
            throw new IllegalArgumentException(e.toString());
        }
    }


    private static native Class<?> defineClass0(ClassLoader loader, String name,
                                                byte[] b, int off, int len);
}
