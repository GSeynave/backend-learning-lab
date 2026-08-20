package com.gauthier.lab.springcore.beans.configuration;

public class DemoService {
    private final ConfigurationDemoRepository repository;

    public DemoService(ConfigurationDemoRepository repository) {
        this.repository = repository;
    }

    public ConfigurationDemoRepository repository(){
        return repository;
    }
}
