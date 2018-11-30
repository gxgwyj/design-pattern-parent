package com.xyz.designpattern.state;

/**
 * Door 状态抽象类
 * Created by Lenovo on 2018/11/25.
 */
public abstract class DoorState {
    protected Door2 door;

    public abstract void touch();

    public void complete() {

    }

    public void timeout() {

    }

    public String status() {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf('.') + 1);
    }

    public DoorState(Door2 door) {
        this.door = door;
    }
}
