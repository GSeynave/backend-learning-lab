package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecureTokenConfiguration {

    @Bean
    public SecureToken secureToken(){
        return SecureToken.create();
    }
}

