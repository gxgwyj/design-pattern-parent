package com.xyz.designpattern.factory;

/**
 * 类: HpFactory <br>
 * 描述: ${DESCRIPTION}<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 14:16
 */
public class HpFactory implements PcFactory{

    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public KeyBo createKeyBo() {
        return new HpKeyBo();
    }
}
