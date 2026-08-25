package com.gauthier.lab.springcore.configuration.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEnvironmentService implements EnvironmentService{
    @Override
    public String environment() {
        return "PROD";
    }
}
