package com.gauthier.lab.springcore.di.beanresolution;


import org.springframework.context.annotation.Primary;

@Primary
public class PrimaryEmailMessageSender implements MessageSender{

    @Override
    public void send(String message) {
        System.out.println("EMAIL:" + message);
    }
}
