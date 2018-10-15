package com.xyz.designpattern.templatemethod;

/**
 * 类: Game <br>
 * 描述: 游戏类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月15日 16:23
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //模板方法
    public final void play() {
        //初始化游戏
        initialize();
        //开始游戏
        startPlay();
        //结束游戏
        endPlay();
    }
}
