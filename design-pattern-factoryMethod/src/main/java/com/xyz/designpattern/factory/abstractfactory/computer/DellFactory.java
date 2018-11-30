package com.xyz.designpattern.factory.abstractfactory.computer;


/**
 * 类: HpFactory <br>
 * 描述: 戴尔工厂<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 14:16
 */
public class DellFactory implements PcFactory{

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public KeyBo createKeyBo() {
        return new DellKeyBo();
    }
}
