package com.zimu.design.decorator;

public class ConcreteComponent implements DecoratorComponent {
    @Override
    public void sampleOperation() {
        System.out.println("具体业务!");
    }
}
