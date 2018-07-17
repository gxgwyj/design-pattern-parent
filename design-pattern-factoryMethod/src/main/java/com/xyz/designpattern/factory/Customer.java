package com.xyz.designpattern.factory;

/**
 * 类: Customer <br>
 * 描述: 工厂方法模式<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月17日 12:54
 */
public class Customer {
    public static void main(String[] args) {
        Factory factory = new Factory2();
        factory.createProduct();
    }
}
