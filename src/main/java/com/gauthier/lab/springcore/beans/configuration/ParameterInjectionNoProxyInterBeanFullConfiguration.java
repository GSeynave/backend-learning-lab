package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ParameterInjectionNoProxyInterBeanFullConfiguration {

    @Bean
    public ConfigurationDemoRepository paramInjectionDemoRepository(){
        System.out.println("DemoRepository FACTORY");
        return new ConfigurationDemoRepository();
    }

    @Bean
    DemoService paramInjectionDemoService(ConfigurationDemoRepository repository){
        return new DemoService(repository);
    }
}
