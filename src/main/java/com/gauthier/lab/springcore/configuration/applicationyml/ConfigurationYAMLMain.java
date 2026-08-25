package com.gauthier.lab.springcore.configuration.applicationyml;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationYAMLMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("4.1 - Configuration application.yml");
        System.out.println("================================");

        configurationProbe();

        System.out.println("================================");
        System.out.println("End of Configuration application.yml");
        System.out.println("================================");

    }

    private static void configurationProbe() {
        System.out.println("Exercise A - Basic property");
        try (var context = new SpringApplicationBuilder(ConfigAppStartup.class).web(WebApplicationType.NONE).run()) {
            ConfigurationYamlProbe configProbe= context.getBean(ConfigurationYamlProbe.class);
            configProbe.print();

        } catch (Exception e) {
            System.out.println("Configuration yaml main exception : " + e);
        }
        System.out.println("================================");
    }

}
