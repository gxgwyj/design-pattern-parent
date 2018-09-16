package com.xyz.designpattern.proxy.service.impl;

import com.xyz.designpattern.proxy.service.UserService;

/**
 * 类: UserServiceImpl <br>
 * 描述: 用户服务实现类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年09月15日 15:05
 */
public class UserServiceImpl implements UserService {
    public void addUser() {
        System.out.println("添加用户");
    }

    public void updateUser() {
        System.out.println("更新用户");
    }
}
