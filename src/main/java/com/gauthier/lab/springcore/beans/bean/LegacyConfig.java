package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LegacyConfig {

    @Bean(initMethod = "open", destroyMethod = "close")
    public LegacyResource legacyResource(){
        return new LegacyResource();
    }
}
