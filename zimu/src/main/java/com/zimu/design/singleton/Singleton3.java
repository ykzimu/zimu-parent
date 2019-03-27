package com.zimu.design.singleton;

/**
 * 单例模式（线程安全，但是影响性能）
 */
public class Singleton3 extends Singleton {

    private static Singleton3 instance = null;

    private Singleton3() {
        super();
    }

    /**
     * 方法锁，性能有问题，多线程下，会有等待
     */
    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }

}
