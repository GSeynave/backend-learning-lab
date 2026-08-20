package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.stereotype.Repository;

@Repository
public class CreationRepository {

    public CreationRepository() {
        System.out.println("1 - CreationRepository constructor");
    }
}
