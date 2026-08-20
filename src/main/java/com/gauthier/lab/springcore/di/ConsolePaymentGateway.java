package com.gauthier.lab.springcore.di;


public class ConsolePaymentGateway implements PaymentGateway{

    @Override
    public void charge(Double amount){
        System.out.println("Charging " + amount);
    }
}
