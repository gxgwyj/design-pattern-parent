package com.xyz.designpattern.singleton;

/**
 * 类: Singleton <br>
 * 描述: 懒汉式单例模式，线程非安全，非严格意义上的单例模式<br>
 * 作者: gaoxugang <br>
 * 时间: 2018年07月08日 19:32
 */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){}

    public static LazySingleton getInstance() {
        if (instance == null) {
            return new LazySingleton();
        }
        return instance;
    }
}
