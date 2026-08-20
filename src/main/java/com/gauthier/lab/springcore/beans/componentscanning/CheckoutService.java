package com.gauthier.lab.springcore.beans.componentscanning;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CheckoutService {

    private final PaymentGateway gateway;

    public CheckoutService(PaymentGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    public void checkout(String orderId){
        gateway.pay(orderId);
    }
}
