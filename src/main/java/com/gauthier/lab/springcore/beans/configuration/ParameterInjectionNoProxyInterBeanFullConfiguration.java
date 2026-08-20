package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ParameterInjectionNoProxyInterBeanFullConfiguration {

    @Bean
    public DemoRepository demoRepository(){
        System.out.println("DemoRepository FACTORY");
        return new DemoRepository();
    }

    @Bean DemoService demoService(DemoRepository repository){
        return new DemoService(repository);
    }
}
