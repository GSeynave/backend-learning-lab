package com.gauthier.lab.springcore.springboot.starters;

import com.gauthier.lab.springcore.configuration.configurationproperties.ConfigurationPropertiesAppStartup;
import com.gauthier.lab.springcore.springboot.autoconfig.AcademyFeature;
import com.gauthier.lab.springcore.springboot.autoconfig.UserAcademyConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class StartersMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("5.1 - Auto Configuration");
        System.out.println("================================");

        autoConfig();
        autoConfigB();
        //autoConfigC();

        System.out.println("================================");
        System.out.println("End of Auto Configuration");
        System.out.println("================================");

    }

    private static void autoConfig() {
        System.out.println("Exercise A ");
        try (var context = new SpringApplicationBuilder(ConfigurationPropertiesAppStartup.class)
                .web(WebApplicationType.NONE)
                .run("--debug")
        ) {
        } catch (Exception e) {
            System.out.println("Auto Configuration main exception : " + e);
        }
        System.out.println("================================");
    }
    private static void autoConfigB() {
        System.out.println("Exercise B ");
        try (var context = new SpringApplicationBuilder()
                .web(WebApplicationType.NONE)
                .run("--debug")
        ) {
            context.getBean(AcademyFeature.class);
        } catch (Exception e) {
            System.out.println("Auto Configuration main exception : " + e);
        }
        System.out.println("================================");
    }
    private static void autoConfigC() {
        System.out.println("Exercise C ");
        try (var context = new SpringApplicationBuilder(UserAcademyConfiguration.class)
                .web(WebApplicationType.NONE)
                .run("--debug")
        ) {
            context.getBean(AcademyFeature.class);
        } catch (Exception e) {
            System.out.println("Auto Configuration main exception : " + e);
        }
        System.out.println("================================");
    }

}
