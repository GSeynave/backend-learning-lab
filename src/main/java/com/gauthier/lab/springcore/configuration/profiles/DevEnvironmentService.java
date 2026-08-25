package com.gauthier.lab.springcore.configuration.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEnvironmentService implements EnvironmentService{
    @Override
    public String environment() {
        return "DEV";
    }
}
