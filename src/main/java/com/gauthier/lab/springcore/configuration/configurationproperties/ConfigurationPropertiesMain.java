package com.gauthier.lab.springcore.configuration.configurationproperties;

import com.gauthier.lab.springcore.configuration.applicationyml.ConfigAppStartup;
import com.gauthier.lab.springcore.configuration.applicationyml.ConfigurationYamlProbe;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ConfigurationPropertiesMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("4.4 - Configuration properties");
        System.out.println("================================");

        configurationProbe();

        System.out.println("================================");
        System.out.println("End of Configuration properties");
        System.out.println("================================");

    }

    private static void configurationProbe() {
        System.out.println("Exercise A ");
        try (var context = new SpringApplicationBuilder(ConfigurationPropertiesAppStartup.class).web(WebApplicationType.NONE).run()) {
            AcademyProperties properties= context.getBean(AcademyProperties.class);
            System.out.println("Name: " + properties.name());
            System.out.println("Lesson duration: " + properties.lessonDuration().toString());
            System.out.println("Max students: " + properties.maxStudents());
            System.out.println("Documentation Url: " + properties.documentationUrl());


        } catch (Exception e) {
            System.out.println("Configuration properties main exception : " + e);
        }
        System.out.println("================================");
    }

}
