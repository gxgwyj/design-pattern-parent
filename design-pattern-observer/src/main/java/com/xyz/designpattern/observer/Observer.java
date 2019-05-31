package com.xyz.designpattern.observer;


/**
 * 观察者模式，发布订阅模式
 */
public abstract class Observer {
    /**
     * 被观察的对象
     */
    protected Subject subject;
    public abstract void update();
}
