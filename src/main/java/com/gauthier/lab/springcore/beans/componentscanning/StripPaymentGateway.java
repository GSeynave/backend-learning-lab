package com.gauthier.lab.springcore.beans.componentscanning;

import org.springframework.stereotype.Component;

@Component
public class StripPaymentGateway implements PaymentGateway{
    @Override
    public void pay(String orderId) {
        System.out.println("Stipe payment for "+ orderId
        );

    }
}
