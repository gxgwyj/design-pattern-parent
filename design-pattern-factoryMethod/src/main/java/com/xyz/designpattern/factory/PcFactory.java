package com.xyz.designpattern.factory;

/**
 * 类: PcFactory <br>
 * 描述: PC 工厂类<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 14:07
 */
public interface PcFactory {
    Mouse createMouse();
    KeyBo createKeyBo();
}
