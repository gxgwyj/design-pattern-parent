package com.xyz.designpattern.proxy.service.impl;

import com.xyz.designpattern.proxy.service.BizService;

/**
 * 类: BizTarget <br>
 * 描述: 业务目标方法<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月15日 13:50
 */
public class BizServiceImpl implements BizService {
    public void method() {
        System.out.println("target 方法执行");
    }
}
