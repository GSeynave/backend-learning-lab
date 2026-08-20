package com.gauthier.lab.springcore.beans.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LifecycleService  {

    private LifecycleRepository repository;

    public LifecycleService() {
        System.out.println("3 - Service constructor");
    }

    @Autowired
    void setRepository(LifecycleRepository repository){
        System.out.println("4 - Service setter");
        this.repository = repository;
    }

    @PostConstruct
    void initi(){
        System.out.println("5 - Service postConstruct");

        if(repository == null){
            throw new IllegalStateException("Repository should already exists");
        }
    }

    public void execute(){
        System.out.println("6 - Service business method");
    }

    @PreDestroy
    void destroy(){
        System.out.println("7 - Service preDestroy");
    }
}
