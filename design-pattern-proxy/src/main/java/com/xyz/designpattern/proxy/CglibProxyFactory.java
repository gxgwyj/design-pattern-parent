package com.xyz.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 类: CglibProxyFactory <br>
 * 描述: Cglib 子类代理工厂<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月11日 9:49
 */
public class CglibProxyFactory implements MethodInterceptor {

    //目标对象
    private Object target;

    //构造方法
    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("method before");
        Object returnValue = method.invoke(target,objects);
        System.out.println("method after");
        return returnValue;
    }
}
