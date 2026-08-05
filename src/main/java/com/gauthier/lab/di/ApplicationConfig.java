package com.gauthier.lab.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    PaymentGateway paymentGateway(){
        return new ConsolePaymentGateway();
    }

    @Bean
    OrderRepository orderRepository(){
        return new InMemoryOrderRepository();
    }

    @Bean
    CheckoutService checkoutService(PaymentGateway paymentGateway, OrderRepository orderRepository){
        return new CheckoutService(paymentGateway, orderRepository);
    }
}
