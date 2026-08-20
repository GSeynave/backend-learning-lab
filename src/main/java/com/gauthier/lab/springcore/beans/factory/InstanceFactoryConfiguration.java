package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstanceFactoryConfiguration {

    @Bean
    ClientFactory instanceClientFactory(){
        return new ClientFactory();
    }

    @Bean
    DemoClient instanceDemoClient(ClientFactory clientFactory){
        return clientFactory.createDemoClient();
    }
}
