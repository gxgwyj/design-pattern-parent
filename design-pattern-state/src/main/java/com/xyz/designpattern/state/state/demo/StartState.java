package com.xyz.designpattern.state.state.demo;

/**
 * 类: StartState <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年05月15日 17:17
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
