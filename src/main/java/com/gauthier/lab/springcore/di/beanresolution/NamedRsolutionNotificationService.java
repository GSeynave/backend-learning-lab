package com.gauthier.lab.springcore.di.beanresolution;

import java.util.Objects;

public class NamedRsolutionNotificationService {

    private final MessageSender sender;

    // Bean resolution is made through name of the parameters : Dangerous as wiring is not obvious and can be broken easily
    public NamedRsolutionNotificationService(MessageSender smsSender) {
        this.sender = Objects.requireNonNull(smsSender);
    }

    public void notify(String message){
        sender.send(message);
    }
}
