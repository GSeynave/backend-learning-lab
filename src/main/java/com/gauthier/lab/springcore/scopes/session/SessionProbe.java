package com.gauthier.lab.springcore.scopes.session;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class SessionProbe {

    private final UUID id = UUID.randomUUID();

    UUID id(){
        return id;
    }

    @PostConstruct
    void init(){
        System.out.println("INIT " + id);
    }

    @PreDestroy
    void destroy(){
        System.out.println("DESTROY " + id );
    }
}
