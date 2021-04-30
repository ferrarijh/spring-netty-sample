package com.jonathan.springnettytest.car;

import com.jonathan.springnettytest.car.engine.DieselEngine;
import com.jonathan.springnettytest.car.engine.Engine;
import com.jonathan.springnettytest.car.engine.PetrolEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CarConfig {
    @Bean
    public Car getCar(Engine engine){
        return new Car(engine);
    }

    @Primary
    @Bean(name="dieselEngine")
    public Engine dieselEngine(){
        return new DieselEngine();
    }

    @Bean(name="petrolEngine")
    public Engine petrolEngine(){
        return new PetrolEngine();
    }
}
