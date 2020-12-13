package com.xyz.designpattern.jdkdemo;

import java.lang.reflect.InvocationHandler;

/**
 * @author gaoxugang
 * @data 2020/12/13 0013 14:17
 * @description jdk Proxy demo
 */
public class Proxy implements java.io.Serializable{

    private static final long serialVersionUID = -4778784062797265715L;

    /**
     * 生成代理类构造参数
     */
    private static final Class<?>[] constructorParams = {InvocationHandler.class};

}
