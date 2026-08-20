package com.gauthier.lab.springcore.beans.bean;

public class AuditRepository {
    public void save(String value) {
        System.out.println("AUDIT: " + value);
    }
}
