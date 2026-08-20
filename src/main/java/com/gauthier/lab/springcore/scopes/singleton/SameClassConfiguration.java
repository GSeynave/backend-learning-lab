package com.gauthier.lab.springcore.scopes.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SameClassConfiguration {

    @Bean
    DemoClient clientA(){
        return new DemoClient();
    }
    @Bean
    DemoClient clientB(){
        return new DemoClient();
    }
}
