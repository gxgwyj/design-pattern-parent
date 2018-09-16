package com.xyz.designpattern.proxy;

import com.xyz.designpattern.proxy.handler.DynamicProxyHandler;
import com.xyz.designpattern.proxy.service.BizService;
import com.xyz.designpattern.proxy.service.UserService;
import com.xyz.designpattern.proxy.service.impl.BizServiceImpl;
import com.xyz.designpattern.proxy.service.impl.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * 类: DynamicProxyTest <br>
 * 描述: 动态代理测试，使用JDK提供的Proxy包中的相关类创建目标类的<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月15日 14:23
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        BizService bizService = new BizServiceImpl();
        BizService proxyBizService = (BizService) Proxy.newProxyInstance(BizService.class.getClassLoader(), bizService.getClass().getInterfaces(), new DynamicProxyHandler(bizService));
        proxyBizService.method();

        UserService userService = new UserServiceImpl();
        UserService proxyUserService = (UserService) Proxy.newProxyInstance(UserService.class.getClassLoader(), userService.getClass().getInterfaces(), new DynamicProxyHandler(userService));
        proxyUserService.addUser();
        proxyUserService.updateUser();


    }
}
