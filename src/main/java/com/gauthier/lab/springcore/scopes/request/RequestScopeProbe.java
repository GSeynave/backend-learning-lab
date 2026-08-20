package com.gauthier.lab.springcore.scopes.request;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@RequestScope
@Component
public class RequestScopeProbe {

    private final UUID id = UUID.randomUUID();

    public UUID id(){
        return id;
    }

    @PostConstruct
    void init(){
        System.out.println("INIT : "+id);
    }
    @PreDestroy
    void destroy(){
        System.out.println("DESTROY : "+id);
    }
}
