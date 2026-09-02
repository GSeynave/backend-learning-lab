package com.gauthier.lab.springcore.springboot.applciationrun.starters;

import com.gauthier.lab.springcore.di.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

public class ApplicationRunMain {

    public void execute(String[] args) {

        System.out.println("================================");
        System.out.println("5.4 - Application Run");
        System.out.println("================================");

        inspectStarters(args);

        System.out.println("================================");
        System.out.println("End of Application Run");
        System.out.println("================================");

    }

    private static void inspectStarters(String[] args) {
        SpringApplication application = new SpringApplication(ApplicationRunConfiguration.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.addListeners(event -> {
            System.out.println("ApplicationRunMain - Event: " + event.getClass().getSimpleName());
        });
        application.run(args);

        System.out.println("================================");
    }
}
