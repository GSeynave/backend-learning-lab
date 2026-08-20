package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryFailureConfiguration {

    @Bean
    DemoClient demoClient(){
        throw  new IllegalStateException("Factory creation failed");
    }
}
