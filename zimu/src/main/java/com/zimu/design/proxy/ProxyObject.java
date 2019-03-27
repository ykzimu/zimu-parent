package com.zimu.design.proxy;

public class ProxyObject extends AbstractObject {

    private RealObject realObject;

    public ProxyObject(RealObject realObject) {
        this.realObject = realObject;
    }

    @Override
    public void operation() {
        before();
        realObject.operation();
        after();
    }

    private void before() {
        System.out.println("before......");
    }

    private void after() {
        System.out.println("after......");
    }
}
