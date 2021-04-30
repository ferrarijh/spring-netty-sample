package com.jonathan.springnettytest.car;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.jonathan.springnettytest.car")
public class CarApplication {

    private final Car car;

    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
        return (ApplicationReadyEvent e)->{
            car.start();
        };
    }

}
