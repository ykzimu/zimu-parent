package com.zimu.design.singleton;

/**
 * 单例模式（线程安全 懒加载）
 */
public class Singleton5 extends Singleton {

    private Singleton5() {
        super();
    }


    private static class Singleton5Holder {
        static final Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Singleton5Holder.INSTANCE;
    }

}
