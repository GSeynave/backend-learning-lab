package com.gauthier.lab.springcore.beans.configuration;

public class DemoService {
    private final DemoRepository repository;

    public DemoService(DemoRepository repository) {
        this.repository = repository;
    }

    public DemoRepository repository(){
        return repository;
    }
}
