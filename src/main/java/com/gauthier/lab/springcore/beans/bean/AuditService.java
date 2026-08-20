package com.gauthier.lab.springcore.beans.bean;

import java.util.Objects;

public class AuditService {
    private final AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    public void audit(String message){
        repository.save(message);
    }
}
