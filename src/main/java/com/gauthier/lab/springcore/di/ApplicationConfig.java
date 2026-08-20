package com.gauthier.lab.springcore.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    PaymentGateway diPaymentGateway(){
        return new ConsolePaymentGateway();
    }

    @Bean
    OrderRepository diOrderRepository(){
        return new InMemoryOrderRepository();
    }

    @Bean
    CheckoutService diCheckoutService(PaymentGateway paymentGateway, OrderRepository orderRepository){
        return new CheckoutService(paymentGateway, orderRepository);
    }
}
