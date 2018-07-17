package com.xyz.designpattern.factory;

/**
 * 类: Factory1 <br>
 * 描述: ${DESCRIPTION}<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 12:52
 */
public class Factory2 implements Factory {
    @Override
    public AbstractProduct createProduct() {
        return new Product2();
    }
}
