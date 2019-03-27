package com.zimu.design.singleton;

/**
 * 单例模式（线程安全 懒加载）
 */
public class Singleton6 extends Singleton {

    private static Singleton6 instance = null;

    private Singleton6() {
        super();
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new Singleton6();
        }
    }

    public static Singleton6 getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }

}
