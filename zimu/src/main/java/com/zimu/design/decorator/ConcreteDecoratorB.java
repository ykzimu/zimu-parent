package com.zimu.design.decorator;

public class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(DecoratorComponent decoratorComponent) {
        super(decoratorComponent);
    }

    @Override
    public void sampleOperation() {
        System.out.println("before Decorator! ConcreteDecoratorB");
        super.sampleOperation();
        System.out.println("after Decorator! ConcreteDecoratorB");
    }
}
