package com.gauthier.lab.springcore.configuration.environmentvar;

import com.gauthier.lab.springcore.configuration.profiles.ConfigProfilesAppStartup;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class EnvironmentVariableMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("4.3 - Environment Variables ");
        System.out.println("================================");

        overrideYaml();

        System.out.println("================================");
        System.out.println("End of Environment Variables ");
        System.out.println("================================");

    }

    private static void overrideYaml() {
        System.out.println("Exercise A - ");
        try (var context = new SpringApplicationBuilder(ConfigProfilesAppStartup.class)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run("--academy.env-var.mode=command-line")) {
            Environment environment= context.getBean(Environment.class);
            System.out.println("academy.env-var.mode : " + environment.getProperty("academy.env-var.mode"));
            System.out.println("academy.env-var.max-students : " + environment.getProperty("academy.env-var.max-students"));
        } catch (Exception e) {
            System.out.println("Profiles main exception : " + e);
        }
        System.out.println("================================");
    }

}
