package com.gauthier.lab.springcore.beans.bean;

public class NotificationService {

    private final MessageSender sender;

    public NotificationService(MessageSender sender) {
        this.sender = sender;
    }
}
