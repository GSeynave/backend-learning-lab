package com.gauthier.lab.di.fieldsetterinjection;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterSecurityService {

    private AuditRepository repository;
    private AlertSender sender;


    @Autowired
    public void setSender(AlertSender sender) {
        this.sender = sender;
    }

    @Autowired
    public void setRepository(AuditRepository repository) {
        this.repository = repository;
    }

    public void detect(String event){
        repository.save(event);
        sender.send(event);
    }
}
