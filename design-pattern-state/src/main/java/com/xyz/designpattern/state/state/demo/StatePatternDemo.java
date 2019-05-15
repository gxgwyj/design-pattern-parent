package com.xyz.designpattern.state.state.demo;

/**
 * 类: StatePatternDemo <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2019年05月15日 17:25
 */
public class StatePatternDemo {
    public static void main(String[] args) {

        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);
        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);
        System.out.println(context.getState().toString());
    }
}
