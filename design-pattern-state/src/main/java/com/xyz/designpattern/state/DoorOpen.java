package com.xyz.designpattern.state;

/**
 * Created by Lenovo on 2018/11/25.
 */
public class DoorOpen extends DoorState {

    public DoorOpen(Door2 door) {
        super(door);
    }

    @Override
    public void touch() {
        door.setState(door.STAYOPEN);
    }
}
