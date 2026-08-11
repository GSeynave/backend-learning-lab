package com.gauthier.lab.di.beanresolution;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Objects;

public class QualifiedNotificationService {

    private final MessageSender sender;

    public QualifiedNotificationService(@Qualifier("smsSender") MessageSender sender) {
        this.sender = Objects.requireNonNull(sender);
    }

    public void notify(String message){
        sender.send(message);
    }
}
