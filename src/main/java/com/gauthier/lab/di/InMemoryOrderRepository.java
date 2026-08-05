package com.gauthier.lab.di;


import org.springframework.core.annotation.Order;

public class InMemoryOrderRepository implements OrderRepository {

    @Override
    public void save(Object order){
        System.out.println("Saving Order " + order.toString());
    }
}
