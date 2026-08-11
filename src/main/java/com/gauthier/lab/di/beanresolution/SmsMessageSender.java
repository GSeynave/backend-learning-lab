package com.gauthier.lab.di.beanresolution;

public class SmsMessageSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
