package com.xyz.designpattern.state.state;


import com.xyz.designpattern.state.DoorNew;

/**
 * Door 状态抽象类
 * Created by Lenovo on 2018/11/25.
 */
public abstract class DoorState {

    /**
     * 状态对象中包含主题对象
     */
    protected DoorNew door;

    /**
     * 构造方法
     * @param door
     */
    public DoorState(DoorNew door) {
        this.door = door;
    }

    public abstract DoorState touch();

    public String status() {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf('.') + 1);
    }
}
