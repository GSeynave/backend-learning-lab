package com.gauthier.lab;

import com.gauthier.lab.di.*;
import com.gauthier.lab.di.Exercice.DIExercice;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);

        //manualDependencyInjection();
        //springManagedDependencyInjection();
        var diExercice = new DIExercice();
        diExercice.runExercice();

    }

    private static void manualDependencyInjection() {
    /*
    ================== Manual Dependency Injection ====================
    This method is responsible to give (inject) the dependencies in the other classes.
    It handles creation, implementation.
    Here CheckoutService simply need to apply business logic, nothing else. It's dependency are provided from an external source (IoC).
    And they are injected from outside, it doesn't need to create or search for them (DI).
     */
        // DI
        PaymentGateway paymentGateway = new ConsolePaymentGateway();
        OrderRepository orderRepository = new InMemoryOrderRepository();

        CheckoutService checkoutService = new CheckoutService(
                paymentGateway, orderRepository
        );
        checkoutService.checkout(new Object());
    }

    /**
     * ================== Manual Dependency Injection ====================
     * Here we let spring handle the injection in the CheckoutService. All the bean we need are declare in the @Configuration class
     * See : ApplicationConfig. We don't inject any class in any other by doing a new in the class parameter.
     * Instead we declare bean, and we simply say for classes that have dependencies that the follow bean will be requires by passing them in parameters.
     * Then we do a new Service(Param1,...)
     **/
    private static void springManagedDependencyInjection() {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {

            CheckoutService checkoutService = context.getBean(CheckoutService.class);
            checkoutService.checkout(new Object());
        }

    }
}
