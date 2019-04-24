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

    public final DoorState CLOSED = new DoorClosed(this);
    public final DoorState CLOSING = new DoorClosing(this);
    public final DoorState OPEN = new DoorOpen(this);
    public final DoorState OPENING = new DoorOpening(this);
    public final DoorState STAYOPEN = new DoorStayOpen(this);

    private DoorState state = CLOSED;

    public void complete() {
        state.complete();
    }

    public String status() {
        return state.status();
    }

    public void timeout() {
        state.timeout();
    }

    public void touch() {
        state.touch();
    }

    public void setState(DoorState state) {
        this.state = state;
        setChanged();
        notifyObservers();
    }
}
