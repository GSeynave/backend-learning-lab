package com.gauthier.lab.di.beanresolution;

import java.util.Objects;

public class NotificationService {

    private final MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = Objects.requireNonNull(sender);
    }

    public void notify(String message){
        sender.send(message);
    }
}
