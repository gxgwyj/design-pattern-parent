package com.xyz.designpattern.singleton;

/**
 * 类: SynchronizedSingleton <br>
 * 描述: 线程同步的单例模式，是线程安全，但是执行效率太低，每次获取单例的时候都必须在方法上加锁，实际上
 * 初始化单例的方法在系统中只需要执行一次，随后其他地方获取单例的时候直接返回，所以不建议使用<br>
 * 作者:  Administrator<br>
 * 时间: 2018年07月30日 14:32
 */
public class SynchronizedSingleton {

    private static SynchronizedSingleton singleton;

    private SynchronizedSingleton() {
    }

    public static synchronized SynchronizedSingleton getSingleton() {
        if (singleton == null) {
            singleton = new SynchronizedSingleton();
        }
        return singleton;
    }
}
