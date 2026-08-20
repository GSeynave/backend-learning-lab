package com.gauthier.lab.springcore.scopes.singleton;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("3.1 - Singleton scope");
        System.out.println("================================");

        sameBeanLookup();
        sameBeanTwoDefinitions();
        twoContainers();
        manual();
        sharedDependency();

        System.out.println("================================");
        System.out.println("End of Singleton scope");
        System.out.println("================================");

    }

    private static void sameBeanLookup() {
        System.out.println("Exercise A - Same Bean Lookup");
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.springcore.scopes.singleton");
            context.refresh();
            var a = context.getBean(SingletonProbe.class);
            var b = context.getBean(SingletonProbe.class);
            System.out.println("clientA == clientB : ");
            System.out.println(a == b);
        } catch (Exception e) {
            System.out.println("Singleton main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void sameBeanTwoDefinitions() {
        System.out.println("Exercise B - Same Bean, Two Definitions");
        try (var context = new AnnotationConfigApplicationContext(SameClassConfiguration.class)) {
            var clientA = context.getBean("clientA");
            var clientAAgain = context.getBean("clientA");
            var clientB = context.getBean("clientB");
            System.out.println("client A is equal to client A again : ");
            System.out.println(clientA == clientAAgain);
            System.out.println("client A is equal to client B : ");
            System.out.println(clientA == clientB);
        } catch (Exception e) {
            System.out.println("Singleton main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void twoContainers() {
        System.out.println("Exercise C - Two Containers");
        try (var context = new AnnotationConfigApplicationContext(SameClassConfiguration.class)) {
            var client1 = context.getBean("clientA");
            try (var context2 = new AnnotationConfigApplicationContext(SameClassConfiguration.class)) {
                var client2 = context2.getBean("clientA");
                System.out.println("Client1 from container 1 == client 2 from container 2 : " );
                System.out.println(client1 == client2);
            } catch (Exception e) {
                System.out.println("Singleton main exception : " + e);
            }

        } catch (Exception e) {
            System.out.println("Singleton main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void manual() {
        System.out.println("Exercise D - Manual");
        try (var context = new AnnotationConfigApplicationContext(SameClassConfiguration.class)) {
            var managed = context.getBean("clientA");
            var manual = new DemoClient();
            System.out.println("Is managed = to manual : " );
            System.out.println(managed.equals(manual));

        } catch (Exception e) {
            System.out.println("Singleton main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void sharedDependency() {
        System.out.println("Exercise E - Shared Dependency");
        try (var context = new AnnotationConfigApplicationContext(SharedDependencyConfiguration.class)) {
            ServiceOne serviceOne = context.getBean(ServiceOne.class);
            ServiceTwo serviceTwo = context.getBean(ServiceTwo.class);
            System.out.println("Does service share the same dependency :" );
            System.out.println(serviceOne.repository() == serviceTwo.repository());

        } catch (Exception e) {
            System.out.println("Singleton main exception : " + e);
        }
        System.out.println("================================");
    }
}
