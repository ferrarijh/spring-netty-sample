package com.jonathan.springnettytest.car.engine;

public class PetrolEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Petrol Engine started..");
    }
}
