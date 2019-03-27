package com.zimu.design.singleton;

/**
 * 单例模式（线程不安全）
 */
public class Singleton1 extends Singleton {

    private static Singleton1 instance = null;

    private Singleton1() {
        super();
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }


}
