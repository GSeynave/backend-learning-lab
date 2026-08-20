package com.gauthier.lab.springcore.beans.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Repository;

@Repository
public class LifecycleRepository {

    public LifecycleRepository() {
        System.out.println("1 - Repository Constructor");
    }

    @PostConstruct
    void initi(){
        System.out.println("2 - Repository postConstruct");
    }

    @PreDestroy
    void destroy(){
        System.out.println("8 - Repository preDestroy");
    }
}
