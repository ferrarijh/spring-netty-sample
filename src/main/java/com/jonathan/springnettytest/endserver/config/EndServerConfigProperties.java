package com.jonathan.springnettytest.endserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="netty")
public class EndServerConfigProperties {
    private int bossCount;
    private int workerCount;
    private int endServerPort1;
}
