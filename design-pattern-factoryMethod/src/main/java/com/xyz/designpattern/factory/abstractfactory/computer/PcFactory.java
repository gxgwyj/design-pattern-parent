package com.xyz.designpattern.factory.abstractfactory.computer;


/**
 * 类: PcFactory  抽象工厂模式 <br>
 * 描述: PC 工厂类<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 14:07
 */
public interface PcFactory {
    Mouse createMouse();
    KeyBo createKeyBo();
}
