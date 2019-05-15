package com.xyz.designpattern.state.state.demo;

/**
 * 类: StopState <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年05月15日 17:17
 */
public class StopState implements State {

    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}
