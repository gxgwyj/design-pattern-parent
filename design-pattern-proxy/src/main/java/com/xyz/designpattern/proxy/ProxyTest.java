package com.xyz.designpattern.proxy;

import com.xyz.designpattern.proxy.service.BizService;
import com.xyz.designpattern.proxy.service.impl.BizServiceImpl;
import com.xyz.designpattern.proxy.service.proxy.ProxyBizService;

/**
 * 类: ProxyTest <br>
 * 描述: 静态代理测试类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月15日 13:53
 */
public class ProxyTest {
    public static void main(String[] args) {
        BizService bizService = new BizServiceImpl();
        BizService proxyService = new ProxyBizService(bizService);
        proxyService.method();
    }
}
