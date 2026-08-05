package com.gauthier.lab.di;


import org.springframework.format.number.money.MonetaryAmountFormatter;

public interface PaymentGateway {

    void charge(Double amount);


}
