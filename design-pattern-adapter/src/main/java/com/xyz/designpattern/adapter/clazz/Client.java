package com.xyz.designpattern.adapter.clazz;

/**
 * 类: Client <br>
 * 描述: 客户端类<br>
 * 作者:  Administrator<br>
 * 时间: 2018年08月26日 15:07
 */
public class Client {
    private static BizService bizService = new BizServiceProcessAdpater();
    public static void main(String[] args) {
        bizService.bizMethod();
    }
}
