package com.xyz.designpattern.singleton;

/**
 * 类: InnerStaticSingleton <br>
 * 描述: 静态内部类模式的单例模式，线程安全，延迟加载，效率较高<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年07月30日 14:46
 */
public class InnerStaticSingleton {
    // 虚拟机保证初始化只初始了一次
    private InnerStaticSingleton() {
    }

    private static class SingleInstance {
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return SingleInstance.INSTANCE;
    }

}
