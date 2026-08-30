package com.gauthier.lab.springcore.springboot.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class AcademyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    AcademyFeature academyFeature(){
        return new AcademyFeature("Auto-configuration");
    }
}

