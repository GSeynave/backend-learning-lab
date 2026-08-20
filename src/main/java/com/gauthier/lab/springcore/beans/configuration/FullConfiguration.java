package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FullConfiguration {

    @Bean
    public ConfigurationDemoRepository demoRepository(){
        System.out.println("DemoRepository FACTORY");

        return new ConfigurationDemoRepository();
    }

}
