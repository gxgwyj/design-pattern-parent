package com.xyz.designpattern.singleton;

/**
 * 类: Singleton <br>
 * 描述: 恶汉式单例模式，线程安全，没有加锁，执行效率高，类加载就初始化，浪费内存<br>
 * 作者:  Administrator<br>
 * 时间: 2018年07月30日 14:05
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }
}
