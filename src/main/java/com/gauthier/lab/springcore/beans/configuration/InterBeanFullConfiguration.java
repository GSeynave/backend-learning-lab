package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterBeanFullConfiguration {

    @Bean
    public ConfigurationDemoRepository configurationDemoRepository(){
        System.out.println("DemoRepository FACTORY");
        return new ConfigurationDemoRepository();
    }

    @Bean DemoService configurationDemoService(){
        return new DemoService(configurationDemoRepository());
    }
}
