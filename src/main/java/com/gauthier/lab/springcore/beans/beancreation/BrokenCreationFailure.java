package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.stereotype.Component;

@Component
public class BrokenCreationFailure {
    public BrokenCreationFailure() {
        System.out.println("Broken constructor invoked");

        throw  new IllegalStateException("Intentional creation failure");
    }
}
