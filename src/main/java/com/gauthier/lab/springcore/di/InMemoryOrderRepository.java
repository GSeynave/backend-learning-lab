package com.gauthier.lab.springcore.di;


public class InMemoryOrderRepository implements OrderRepository {

    @Override
    public void save(Object order){
        System.out.println("Saving Order " + order.toString());
    }
}
