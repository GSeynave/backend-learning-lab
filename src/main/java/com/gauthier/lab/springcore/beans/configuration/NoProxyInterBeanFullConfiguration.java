package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class NoProxyInterBeanFullConfiguration {

    @Bean
    public ConfigurationDemoRepository noProxyInterBeanDemoRepository(){
        System.out.println("DemoRepository FACTORY");
        return new ConfigurationDemoRepository();
    }

    @Bean DemoService noProxyInterBeanDemoService(){
        return new DemoService(noProxyInterBeanDemoRepository());
    }
}
