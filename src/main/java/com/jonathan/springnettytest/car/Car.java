package com.jonathan.springnettytest.car;

import com.jonathan.springnettytest.car.engine.Engine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private final Engine engine;
    private final String name;

    @Autowired
    public Car(@Qualifier("dieselEngine")Engine engine, CarConfig config){
        this.engine = engine;
        this.name = config.name();
    }

    public void start(){
        System.out.println("car["+name+"] started..");
        engine.start();
    }
}
