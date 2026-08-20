package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanConfiguration {

    @Bean
    DemoClientFactoryBean demoClient(){
        return new DemoClientFactoryBean();
    }
}
