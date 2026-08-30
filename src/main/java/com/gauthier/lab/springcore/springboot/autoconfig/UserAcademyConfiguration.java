package com.gauthier.lab.springcore.springboot.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class UserAcademyConfiguration {

    @Bean
    AcademyFeature academyFeature(){
        return new AcademyFeature("user");
    }
}
