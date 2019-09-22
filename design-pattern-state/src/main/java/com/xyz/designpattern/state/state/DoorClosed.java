package com.xyz.designpattern.state.state;


import com.xyz.designpattern.state.DoorNew;

/**
 * Created by Lenovo on 2018/11/25.
 */
public class DoorClosed extends DoorState {

    public DoorClosed(DoorNew door) {
        super(door);
    }

    @Override
    public DoorState touch() {
        return door.OPENING;
    }
}
