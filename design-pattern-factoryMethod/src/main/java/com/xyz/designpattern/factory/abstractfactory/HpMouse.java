package com.xyz.designpattern.factory.abstractfactory;

/**
 * 类: DellMouse <br>
 * 描述: 惠普鼠标<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 14:12
 */
public class HpMouse extends Mouse {
    @Override
    public void sayHi() {
        System.out.println("hp mouse");
    }
}
