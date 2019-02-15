package com.xyz.designpattern.proxy.service.proxy;

import com.xyz.designpattern.proxy.service.BizService;

/**
 * 类: BizProxy <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月15日 13:51
 */
public class ProxyBizService implements BizService {
    private BizService bizService;

    public ProxyBizService(BizService bizService) {
        this.bizService = bizService;
    }

    @Override
    public void method() {
        System.out.println("method before");
        bizService.method();
        System.out.println("method after");
    }
}
