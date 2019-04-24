package com.xyz.designpattern.state.state;

import com.xyz.designpattern.state.DoorNew;

/**
 * Created by Lenovo on 2018/11/25.
 */
public class DoorOpening extends DoorState {

    public DoorOpening(DoorNew door) {
        super(door);
    }

    @Override
    public void touch() {
        door.setState(door.CLOSING);
    }
}
