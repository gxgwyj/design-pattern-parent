package com.xyz.designpattern.templatemethod;

/**
 * 类: Cricket <br>
 * 描述: <br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月15日 16:28
 */
public class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}
