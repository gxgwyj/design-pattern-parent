package com.xyz.designpattern.singleton;

/**
 * 类: DclSingleton <br>
 * 描述: 双重校验锁单例模式，线程安全，懒加载,该方法是对同步锁方法的改良版<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年07月30日 14:15
 */
public class DclSingleton {
    private volatile static DclSingleton singleton;  //volatile 防止指令重排序，必须加volatile 关键字，否则不是线程安全（确保每次都从主内存中读取）

    private DclSingleton() {

    }

    public static DclSingleton getSingleton() {
        if (singleton == null) { //有并发危险，非线程安全
            synchronized (DclSingleton.class) {
                if (singleton == null) {  //并发进来之后，排队执行，如果前面的线程已经创建了单例对象，后面的线程不需要创建
                    singleton = new DclSingleton();
                }
            }
        }
        return singleton;
    }
}
