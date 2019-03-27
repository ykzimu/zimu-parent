package com.zimu.design.decorator;

public class Decorator implements DecoratorComponent {

    private DecoratorComponent decoratorComponent;

    public Decorator(DecoratorComponent decoratorComponent) {
        this.decoratorComponent = decoratorComponent;
    }

    @Override
    public void sampleOperation() {
        decoratorComponent.sampleOperation();
    }
}
