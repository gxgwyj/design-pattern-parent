package com.xyz.designpattern.templatemethod;

/**
 * 类: Football <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月15日 16:30
 */
public class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}
