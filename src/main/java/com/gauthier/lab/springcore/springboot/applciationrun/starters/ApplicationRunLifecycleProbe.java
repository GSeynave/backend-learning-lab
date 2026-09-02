package com.gauthier.lab.springcore.springboot.applciationrun.starters;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunLifecycleProbe {

    public ApplicationRunLifecycleProbe() {
        System.out.println("1 - Constructor");
    }

    @PostConstruct
    void init() {
        System.out.println("2 - PostConstruct");
    }
}
