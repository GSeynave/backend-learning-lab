package com.gauthier.lab.springcore.scopes.singleton;

import org.springframework.stereotype.Component;

@Component
public class SingletonProbe {
    public SingletonProbe() {
        System.out.println("SingletonProbe constructed");
    }
}
