package com.gauthier.lab.springcore.scopes.singleton;

import org.springframework.stereotype.Service;

@Service
public class ServiceOne {
    private final SharedRepository repository;

    public ServiceOne(SharedRepository repository) {
        this.repository = repository;
    }

    public SharedRepository repository() {
        return repository;
    }
}
