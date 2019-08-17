package com.xyz.designpattern.proxy.handler;

import com.xyz.designpattern.proxy.service.UserService;
import com.xyz.designpattern.proxy.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author gaoxugang
 * @data 2019/8/17 0017
 * @description TODO
 */
public class MyProxyHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getSimpleName());
        return null;
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        UserService userService = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(), UserServiceImpl.class.getInterfaces(), new MyProxyHandler());
        userService.updateUser();
    }
}
