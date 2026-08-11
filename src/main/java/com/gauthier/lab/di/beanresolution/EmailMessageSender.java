package com.gauthier.lab.di.beanresolution;


public class EmailMessageSender implements MessageSender{

    @Override
    public void send(String message) {
        System.out.println("EMAIL:" + message);
    }
}
