package com.jonathan.springnettytest.endserver;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringNettyTestEndServerApplication {

    private final EndServer endServer;

    public static void main(String[] args) {
        SpringApplication.run(SpringNettyTestEndServerApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
        return (ApplicationReadyEvent e)->{
            endServer.start();
        };
    }
}
