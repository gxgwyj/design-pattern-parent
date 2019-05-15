package com.xyz.designpattern.state.state;


/**
 * Created by Lenovo on 2018/11/25.
 */
public class DoorClosing extends DoorState {
    @Override
    public DoorState touch() {
        return DoorState.OPENING;
    }
}
