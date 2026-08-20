package com.gauthier.lab.springcore.beans.componentscanning;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan("com.gauthier.lab.spring.beans.componentscanning")
public class ComponentScanningMain {

    public void execute() {
        //springScanning();

        //springScanningExercice();
        noManualScan();
    }

    private static void springScanning() {
        try (var context = new AnnotationConfigApplicationContext()) {
            // We let spring identify component candidate for bean.
            // no manual bean registration
            context.scan("com.gauthier.lab.spring.beans.componentscanning");
            context.refresh();

            CheckoutService service = context.getBean(CheckoutService.class);
            service.checkout("ORDER-123");
        }
    }

    private void springScanningExercice() {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.spring.beans.componentscanning");
            context.refresh();
            GreetingService service = context.getBean(GreetingService.class);
            service.greet("Spring");

            Arrays.stream(context.getBeanDefinitionNames())
                    .filter(name -> name.contains("greeting") || name.contains("console"))
                    .sorted()
                    .forEach(System.out::println);

        }
    }

    private void noManualScan() {
        try (var context = new AnnotationConfigApplicationContext(ComponentScanningMain.class)) {
            GreetingService service = context.getBean(GreetingService.class);
            service.greet("Spring");

            Arrays.stream(context.getBeanDefinitionNames())
                    .filter(name -> name.contains("greeting") || name.contains("console"))
                    .sorted()
                    .forEach(System.out::println);

        } catch (Exception ignored){

        }
    }
}
