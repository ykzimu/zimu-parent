package com.zimu.design.decorator;

public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(DecoratorComponent decoratorComponent) {
        super(decoratorComponent);
    }

    @Override
    public void sampleOperation() {
        System.out.println("before Decorator! ConcreteDecoratorA");
        super.sampleOperation();
        System.out.println("after Decorator! ConcreteDecoratorA");
    }
}
