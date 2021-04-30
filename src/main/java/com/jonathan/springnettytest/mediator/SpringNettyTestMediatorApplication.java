package com.jonathan.springnettytest.mediator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringNettyTestMediatorApplication {

	private final MediatorServer mediatorServer;

	public static void main(String[] args) {
		SpringApplication.run(SpringNettyTestMediatorApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(){
		return (ApplicationReadyEvent e)->{
			mediatorServer.start();
		};
	}
}
