package com.xyz.designpattern.singleton;

/**
 * 类: DclSingleton <br>
 * 描述: 双重校验锁单例模式，线程安全，懒加载,该方法是对同步锁方法的改良版<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年07月30日 14:15
 */
public class DclSingleton {
    private volatile static DclSingleton singleton;

    private DclSingleton() {

    }

    public static DclSingleton getSingleton() {
        if (singleton == null) {
            synchronized (DclSingleton.class) {
                if (singleton == null) {
                    singleton = new DclSingleton();
                }
            }
        }
        return singleton;
    }
}
