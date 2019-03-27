package com.zimu.design.singleton;


/**
 * 单例模式（线程不安全，双重校验锁其实线程并不安全，详见链接<p>http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html</p>）
 */
public class Singleton4 extends Singleton {

    private static Singleton4 instance = null;

    private Singleton4() {
        super();
    }

    /**
     * 方法锁，性能有问题，多线程下，会有等待
     */
    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }

}
