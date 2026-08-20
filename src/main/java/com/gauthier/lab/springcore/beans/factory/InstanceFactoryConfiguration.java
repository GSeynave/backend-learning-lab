package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceFactoryConfiguration {

    @Bean
    ClientFactory clientFactory(){
        return new ClientFactory();
    }

    @Bean
    DemoClient demoClient(ClientFactory clientFactory){
        return clientFactory.createDemoClient();
    }
}
