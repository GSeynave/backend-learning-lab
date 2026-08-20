package com.gauthier.lab.springcore.di.fieldsetterinjection;

import org.springframework.beans.factory.annotation.Autowired;

public class FieldSecurityService {
    @Autowired
    private AuditRepository repository;
    @Autowired
    private AlertSender sender;

    public void detect(String event){
        repository.save(event);
        sender.send(event);
    }
}
