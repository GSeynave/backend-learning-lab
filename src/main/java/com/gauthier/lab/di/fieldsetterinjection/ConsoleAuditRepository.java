package com.gauthier.lab.di.fieldsetterinjection;

public class ConsoleAuditRepository implements AuditRepository {

    @Override
    public void save(String message) {
        System.out.println("AUDIT: " + message);
    }
}
