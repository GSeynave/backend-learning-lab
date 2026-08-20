package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

@Configuration
public class FullConfiguration {

    @Bean
    public DemoRepository demoRepository(){
        System.out.println("DemoRepository FACTORY");

        return new DemoRepository();
    }

}
