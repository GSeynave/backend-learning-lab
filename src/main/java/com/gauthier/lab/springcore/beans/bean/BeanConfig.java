package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public TimeProvider timeProvider() {
        System.out.println("@Bean timeProvider method");
        return new TimeProvider();
    }
}
