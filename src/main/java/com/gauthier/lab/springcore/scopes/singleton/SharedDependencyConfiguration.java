package com.gauthier.lab.springcore.scopes.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedDependencyConfiguration {

    @Bean
    SharedRepository repository(){
        return new SharedRepository();
    }

    @Bean
    ServiceOne serviceOne(SharedRepository repository){
        return new ServiceOne(repository);
    }
    @Bean
    ServiceTwo serviceTwo(SharedRepository repository){
        return new ServiceTwo(repository);
    }
}
