package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.stereotype.Component;

@Component
public class BrokenCreationFailure {
    public BrokenCreationFailure() {
        System.out.println("Broken constructor invoked");

        try {
            throw new IllegalStateException("Intentional creation failure");
        } catch (Exception e){
            System.out.println("Here instantiation fails, catch for running app purpose");
        }
    }
}
