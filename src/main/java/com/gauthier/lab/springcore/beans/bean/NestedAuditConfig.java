package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NestedAuditConfig {

    @Bean
    public AuditService auditService(){
        // AuditRepository is a java object, not a bean managed by spring.
        return new AuditService(new AuditRepository());
    }
}
