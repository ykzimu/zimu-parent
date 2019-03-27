package com.zimu.design.bridge;

public class MyBridge extends Bridge {

    @Override
    public void eat() {
        getAnimal().eat();
    }
}
