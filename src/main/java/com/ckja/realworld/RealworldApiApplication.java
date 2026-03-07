package com.ckja.realworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RealworldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealworldApiApplication.class, args);
    }
}
