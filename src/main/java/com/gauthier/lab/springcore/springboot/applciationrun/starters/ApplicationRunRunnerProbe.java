package com.gauthier.lab.springcore.springboot.applciationrun.starters;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunRunnerProbe implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("3 - CommandLineRunner");
    }
}
