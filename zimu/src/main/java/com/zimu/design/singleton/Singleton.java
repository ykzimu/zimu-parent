package com.zimu.design.singleton;

import java.util.HashSet;
import java.util.Set;

public class Singleton {

    public static volatile Set<Integer> hashCodes = new HashSet<>();

    Singleton() {
        hashCodes.add(this.hashCode());
        System.out.println("init Singleton：" + this.hashCode());
    }

    public Object readResolve() {
        return this;
    }

}
