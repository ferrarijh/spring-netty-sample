package com.jonathan.springnettytest.car.engine;

public class DieselEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Diesel Engine started..");
    }
}
