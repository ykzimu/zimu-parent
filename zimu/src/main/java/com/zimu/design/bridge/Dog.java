package com.zimu.design.bridge;

public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog eat meal!");
    }
}
