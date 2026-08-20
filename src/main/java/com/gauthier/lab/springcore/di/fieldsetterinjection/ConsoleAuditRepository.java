package com.gauthier.lab.springcore.di.fieldsetterinjection;

public class ConsoleAuditRepository implements AuditRepository {

    @Override
    public void save(String message) {
        System.out.println("AUDIT: " + message);
    }
}
