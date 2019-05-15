package com.xyz.designpattern.state.state;


/**
 * Door 状态抽象类
 * Created by Lenovo on 2018/11/25.
 */
public abstract class DoorState {

    /**
     *  是状态成为常量
     */
    public static final DoorState CLOSED = new DoorClosed();
    public static final DoorState CLOSING = new DoorClosing();
    public static final DoorState OPEN = new DoorOpen();
    public static final DoorState OPENING = new DoorOpening();
    public static final DoorState STAYOPEN = new DoorStayOpen();

    public abstract DoorState touch();

    public String status() {
        String s = getClass().getName();
        return s.substring(s.lastIndexOf('.') + 1);
    }
}
