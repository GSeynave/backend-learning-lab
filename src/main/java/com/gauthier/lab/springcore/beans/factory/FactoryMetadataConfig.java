package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class FactoryMetadataConfig {

    @Bean
    public DemoClient demoClient(){
        return new DemoClient();
    }
}
