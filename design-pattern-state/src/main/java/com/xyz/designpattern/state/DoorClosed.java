package com.xyz.designpattern.state;

/**
 * Created by Lenovo on 2018/11/25.
 */
public class DoorClosed extends DoorState {

    public DoorClosed(Door2 door) {
        super(door);
    }

    @Override
    public void touch() {
        door.setState(door.OPENING);
    }
}
