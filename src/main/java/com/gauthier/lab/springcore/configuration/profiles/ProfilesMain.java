package com.gauthier.lab.springcore.configuration.profiles;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;

import java.util.Arrays;

public class ProfilesMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("4.2 - Profiles ");
        System.out.println("================================");

        profileSpecific();
        profileProd();
        beanProfile();
        noBeanForProfile();

        System.out.println("================================");
        System.out.println("End of Profiles ");
        System.out.println("================================");

    }

    private static void profileSpecific() {
        System.out.println("Exercise A - ");
        try (var context = new SpringApplicationBuilder(ConfigProfilesAppStartup.class)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run()) {
            Environment environment= context.getBean(Environment.class);
            System.out.println(environment.getProperty("academy.mode"));
            System.out.println(environment.getProperty("academy.timeout"));
            System.out.println(Arrays.toString(environment.getActiveProfiles()));
        } catch (Exception e) {
            System.out.println("Profiles main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void profileProd() {
        System.out.println("Exercise B - ");
        try (var context = new SpringApplicationBuilder(ConfigProfilesAppStartup.class)
                .web(WebApplicationType.NONE)
                .profiles("prod")
                .run()) {
            Environment environment= context.getBean(Environment.class);
            System.out.println(environment.getProperty("academy.mode"));
            System.out.println(environment.getProperty("academy.timeout"));
            System.out.println(Arrays.toString(environment.getActiveProfiles()));
        } catch (Exception e) {
            System.out.println("Profiles main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void beanProfile() {
        System.out.println("Exercise C - ");
        try (var context = new SpringApplicationBuilder(ConfigProfilesAppStartup.class)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run()) {
            Environment environment= context.getBean(Environment.class);
            EnvironmentService service = context.getBean(EnvironmentService.class);
            System.out.println("Profiles dev, service : " + service.environment());
        } catch (Exception e) {
            System.out.println("Profiles main exception : " + e);
        }
        try (var context = new SpringApplicationBuilder(ConfigProfilesAppStartup.class)
                .web(WebApplicationType.NONE)
                .profiles("prod")
                .run()) {
            Environment environment= context.getBean(Environment.class);
            EnvironmentService service = context.getBean(EnvironmentService.class);
            System.out.println("Profiles prod, service : " + service.environment());
        } catch (Exception e) {
            System.out.println("Profiles main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void noBeanForProfile() {
        System.out.println("Exercise D - ");
        try (var context = new SpringApplicationBuilder(ConfigProfilesAppStartup.class)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run()) {
            Environment environment= context.getBean(Environment.class);
            EnvironmentService service = context.getBean(ProdEnvironmentService.class);
            System.out.println("Profiles dev, service : " + service.environment());
        } catch (Exception e) {
            System.out.println("Profiles main exception : " + e);
        }
        System.out.println("================================");
    }
}
