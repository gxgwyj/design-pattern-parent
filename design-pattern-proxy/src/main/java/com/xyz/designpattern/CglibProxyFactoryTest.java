package com.xyz.designpattern;

import com.xyz.designpattern.proxy.CglibProxyFactory;
import com.xyz.designpattern.proxy.service.impl.UserServiceImpl;

/**
 * 类: CglibProxyFactoryTest <br>
 * 描述: Cglib动态代理测试类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月11日 9:59
 */
public class CglibProxyFactoryTest {

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(userService);
        UserServiceImpl proxyInstance = (UserServiceImpl)cglibProxyFactory.getProxyInstance();
        proxyInstance.addUser();
        proxyInstance.updateUser();
    }
}
