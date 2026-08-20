package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.stereotype.Service;

@Service
public class CreationService {
    private final CreationRepository repository;
    public CreationService(CreationRepository repository) {
        System.out.println("2 - CreationService constructor");

        this.repository = repository;
    }
}
