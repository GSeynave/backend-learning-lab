package com.gauthier.lab.springcore.beans.componentscanning;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = Objects.requireNonNull(greetingRepository);
    }

    public void greet(String name){
        String message = "Hello "+ name;

        greetingRepository.save(message);

        System.out.println(message);
    }
}
