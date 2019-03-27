package com.zimu.design.singleton;

/**
 * 单例模式（线程安全 非懒加载）
 */
public class Singleton2 extends Singleton {

    private static final Singleton2 INSTANCE = new Singleton2();

    private Singleton2() {
        super();
    }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

}
