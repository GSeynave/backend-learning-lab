package com.gauthier.lab.springcore.scopes.prototype;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeProbe {

    public PrototypeProbe() {
        System.out.println("PrototypeProbe constructed");
    }

    @PostConstruct
    void init(){
        System.out.println("INIT");
    }

    @PreDestroy
    void destroy(){
        System.out.println("DESTROY");
    }
}
