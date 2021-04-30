package com.jonathan.springnettytest.car;

import com.jonathan.springnettytest.car.engine.DieselEngine;
import com.jonathan.springnettytest.car.engine.Engine;
import com.jonathan.springnettytest.car.engine.PetrolEngine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(CarConfigProperties.class)
public class CarConfig {

    private final CarConfigProperties carConfigProperties;

    public CarConfig(CarConfigProperties properties){
        this.carConfigProperties = properties;
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

    @Bean
    public String name(){return carConfigProperties.getName();}
}
