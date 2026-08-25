package com.gauthier.lab.springcore.configuration.applicationyml;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationYamlProbe {

    private final Environment environment;

    public ConfigurationYamlProbe(Environment environment) {
        this.environment = environment;
    }

    public void print(){
        System.out.println(environment.getProperty("academy.name"));
        System.out.println(environment.getProperty("academy.lesson-duration"));
        System.out.println(environment.getProperty("academy.description"));
        System.out.println(environment.getProperty("academy.student"));
        try{
            System.out.println(environment.getRequiredProperty("academy.unknown-property"));
        } catch (Exception e){
            System.out.println("Uknown property exception catched "+ e.getMessage());
        }
    }
}
