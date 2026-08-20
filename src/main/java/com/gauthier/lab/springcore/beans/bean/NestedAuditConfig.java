package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NestedAuditConfig {

    @Bean
    public BeanAuditService beanAuditService(){
        // AuditRepository is a java object, not a bean managed by spring.
        return new BeanAuditService(new AuditRepository());
    }
}
