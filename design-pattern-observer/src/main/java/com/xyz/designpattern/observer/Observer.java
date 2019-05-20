package com.xyz.designpattern.observer;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
