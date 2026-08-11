package com.gauthier.lab.di.fieldsetterinjection;

import java.util.Objects;

public class ConstructorSecurityService {
    private final AuditRepository repository;
    private final AlertSender sender;

    public ConstructorSecurityService(AuditRepository repository, AlertSender sender) {
        this.repository = Objects.requireNonNull(repository);
        this.sender = Objects.requireNonNull(sender);
    }

    public void detect(String event){
        repository.save(event);
        sender.send(event);
    }
}
