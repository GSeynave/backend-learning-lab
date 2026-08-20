package com.gauthier.lab.springcore.beans.componentscanning;

import org.springframework.stereotype.Repository;

@Repository
public class ConsoleGreetingRepository implements GreetingRepository {

    @Override
    public void save(String message) {
        System.out.println("SAVE: " + message);
    }
}
