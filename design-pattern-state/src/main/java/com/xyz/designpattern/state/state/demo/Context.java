package com.xyz.designpattern.state.state.demo;

/**
 * 类: Context <br>
 * 描述: 上下文<br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年05月15日 17:14
 */
public class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

}
