package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanCreationMain {

    public void execute() {
        try (var context = new AnnotationConfigApplicationContext()) {
            System.out.println("Exercice 1 - Order of bean creation");
            context.scan("com.gauthier.lab.spring.beans.beancreation");
            System.out.println("BEFORE REFRESH");
            context.refresh();
            System.out.println("AFTER REFRESH");

            System.out.println("Exercice 2 - Getting bean Singleton");
            var service1 = context.getBean(CreationService.class);
            var service2 = context.getBean(CreationService.class);
            System.out.println(service1 == service2);

            System.out.println("Exercice 3 - Lazy bean loading time");
            System.out.println("BEFORE GET");
            context.getBean(LazyReportEngine.class);
            System.out.println("AFTER GET");

            System.out.println("Exercice 4 - Lazy bean required in a non-lazy bean -> NotReallYLazyReportEngine");
            System.out.println("Exercice 5 - Instantiation vs Population -> PopulationService");
            System.out.println("Exercice 6 - Constructor failure -> BrokenCreationService");
        } catch (Exception e){
            System.out.println("Exception while loading context");
        }
    }
}
