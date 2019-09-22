package com.xyz.designpattern.state;

import com.xyz.designpattern.state.state.DoorClosed;
import com.xyz.designpattern.state.state.DoorClosing;
import com.xyz.designpattern.state.state.DoorOpen;
import com.xyz.designpattern.state.state.DoorOpening;
import com.xyz.designpattern.state.state.DoorState;
import com.xyz.designpattern.state.state.DoorStayOpen;

import java.util.Observable;

/**
 * 使用状态模式对原来的代码进行重构，将每个状态写到一个独立的类中
 * Created by Lenovo on 2018/11/25.
 */
public class DoorNew extends Observable {
    /**
     *  每个主体对象中都有该对象所有的状态对象常量（注意不能声明为static，如果为static则非线程安全）
     */
    public  final DoorState CLOSED = new DoorClosed(this);
    public  final DoorState CLOSING = new DoorClosing(this);
    public  final DoorState OPEN = new DoorOpen(this);
    public  final DoorState OPENING = new DoorOpening(this);
    public  final DoorState STAYOPEN = new DoorStayOpen(this);


    /**
     * 默认初始状态
     */
    private DoorState state = CLOSED;

    public String status() {
        return state.status();
    }

    public void touch() {
        state = state.touch();
    }

    public void setState(DoorState state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}
