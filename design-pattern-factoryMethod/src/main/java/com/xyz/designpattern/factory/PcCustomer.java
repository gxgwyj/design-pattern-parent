package com.xyz.designpattern.factory;

/**
 * 类: PcCustomer <br>
 * 描述: 工厂消费者<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 14:21
 */
public class PcCustomer {
    public static void main(String[] args) {
        PcFactory factory = new DellFactory();
        KeyBo keyBo = factory.createKeyBo();
        Mouse mouse = factory.createMouse();
        keyBo.sayHi();
        keyBo.sayHi();
    }
}
