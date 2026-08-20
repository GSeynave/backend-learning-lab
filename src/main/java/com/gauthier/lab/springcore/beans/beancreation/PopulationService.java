package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulationService {
    private CreationRepository repository;

    public PopulationService() {
        System.out.println("PopulationService CONSTRUCTOR - Instantiation");
    }

    @Autowired
    public void setRepository(CreationRepository repository){
        System.out.println("PopulationService SETTER - Population");
        this.repository =repository;

    }
}
