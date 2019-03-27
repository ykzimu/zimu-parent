package com.zimu.design.proxy;

public class RealObject extends AbstractObject {
    @Override
    public void operation() {
        System.out.println("我就是爱音乐！");
    }
}
