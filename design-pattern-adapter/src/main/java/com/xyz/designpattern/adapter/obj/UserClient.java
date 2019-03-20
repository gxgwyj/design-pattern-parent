package com.xyz.designpattern.adapter.obj;

/**
 * 客户端代码
 */
public class UserClient {

    public static void main(String[] args) {
        UserNeedService service = new ServerServiceAdpater();
        service.add();
    }
}
