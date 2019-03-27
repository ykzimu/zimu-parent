package com.zimu.design.bridge;

public abstract class Bridge {

    private Animal animal;

    public void eat() {
        animal.eat();
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
