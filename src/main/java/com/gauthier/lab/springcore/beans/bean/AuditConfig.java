package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

    @Bean("mainAuditRepository")
    public AuditRepository auditRepository(){
        return new AuditRepository();
    }

    @Bean
    public BeanAuditService auditService(AuditRepository repository){
        return new BeanAuditService(repository);
    }
}
