package com.xyz.designpattern.templatemethod;

/**
 * 类: TemplatePatternDemo <br>
 * 描述: 模板方法测试类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月15日 16:31
 */
public class TemplatePatternDemo {

    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
