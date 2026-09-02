package com.gauthier.lab.springcore.springboot.starters;

import com.gauthier.lab.springcore.configuration.configurationproperties.ConfigurationPropertiesAppStartup;
import com.gauthier.lab.springcore.springboot.autoconfig.AcademyFeature;
import com.gauthier.lab.springcore.springboot.autoconfig.UserAcademyConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class StartersMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("5.2 - Starters");
        System.out.println("================================");

        inspectStarters();

        System.out.println("================================");
        System.out.println("End of Starters");
        System.out.println("================================");

    }

    private static void inspectStarters() {
        System.out.println("Exercise A ");
        try (var context = new SpringApplicationBuilder(ConfigurationPropertiesAppStartup.class)
                .web(WebApplicationType.NONE)
                .run()
        ) {
            // run mvn dependency:tree to see the dependencies and check the starters
            // run mvn help:effective-pom to see the effective pom and check the starters
            // Transitive dependencies = dependency of a dependency
            // starter = a dependency set
            // BOM = Bill of Materials = a dependency set with a version management
        } catch (Exception e) {
            System.out.println("Auto Configuration main exception : " + e);
        }
        System.out.println("================================");
    }
}
