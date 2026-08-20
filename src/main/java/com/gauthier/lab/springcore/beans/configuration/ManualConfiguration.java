package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManualConfiguration {

    @Bean
    public DemoRepository demoRepository(){
        System.out.println("DemoRepository FACTORY");

        return new DemoRepository();
    }

}
