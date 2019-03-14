package com.xyz.designpattern.proxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * 类: DynamicProxyHandler <br>
 * 描述: 动态代理类的处理器<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月15日 14:38
 */
public class DynamicProxyHandler implements InvocationHandler {

    //目标对象
    private Object object;

    public DynamicProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String targetClassName = object.getClass().getSimpleName();
        String targetMethodName = method.getName();
        String text = MessageFormat.format("目标类{0},目标方法{1}",targetClassName, targetMethodName);
        System.out.println(text + "开始执行" );
        Object result = method.invoke(object, args);
        System.out.println(text + "结束执行" );
        return result;
    }
}
