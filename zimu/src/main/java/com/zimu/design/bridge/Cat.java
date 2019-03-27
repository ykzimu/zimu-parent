package com.zimu.design.bridge;

public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("Cat eat meal!");
    }
}
