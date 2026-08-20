package com.gauthier.lab.springcore.beans.beanlifecycle;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BrokenInitializationBean {

    public BrokenInitializationBean() {
        System.out.println("Constructor succeeded");
    }

    @PostConstruct
    void initialize(){
        System.out.println("PostConstruct started");
        try {
            throw new IllegalStateException("Intentional lifecycle failure");
        } catch (Exception e){
            System.out.println("Here initialization fails, catch for running app purpose");
        }
    }
}
