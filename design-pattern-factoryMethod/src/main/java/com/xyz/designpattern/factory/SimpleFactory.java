package com.xyz.designpattern.factory;

/**
 * 类: Factory <br>
 * 描述: 简单工厂模式<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月08日 19:31
 */
public class SimpleFactory {

    private static String PHONE = "Phone";
    private static String TELEVISION = "Television";

    public static Goods getGoodsByType(String type){
        if (PHONE.equals(type)){
            return new Phone();
        }
        if (TELEVISION.equals(type)){
            return new Television();
        }
        return null;
    }

    public static void main(String[] args) {
        SimpleFactory.getGoodsByType("");
    }
}
