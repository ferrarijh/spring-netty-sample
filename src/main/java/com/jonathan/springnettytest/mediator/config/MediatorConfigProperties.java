package com.jonathan.springnettytest.mediator.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "netty")
public class MediatorConfigProperties {
    private int mediatorDownstreamPort;

    private int bossCount;

    private int workerCount;
}