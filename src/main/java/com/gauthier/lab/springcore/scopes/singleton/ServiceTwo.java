package com.gauthier.lab.springcore.scopes.singleton;

import org.springframework.stereotype.Service;

@Service
public class ServiceTwo {

    private final SharedRepository repository;

    public ServiceTwo(SharedRepository repository) {
        this.repository = repository;
    }

    public SharedRepository repository(){
        return repository;
    }
}
