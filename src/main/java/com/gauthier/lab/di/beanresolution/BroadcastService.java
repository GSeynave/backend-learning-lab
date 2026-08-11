package com.gauthier.lab.di.beanresolution;

import java.util.List;

public class BroadcastService {

    private final List<MessageSender> senders;


    public BroadcastService(List<MessageSender> senders) {
        this.senders = senders;
    }

    public void broadcast(String message){
        senders.forEach(
                sender -> sender.send(message)
        );
    }
}
