package com.jonathan.springnettytest.car;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="car")
public class CarConfigProperties {

    private String name;
}