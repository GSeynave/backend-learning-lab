package com.gauthier.lab.di;


public class CheckoutService {

    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;

    public CheckoutService(PaymentGateway paymentGateway, OrderRepository orderRepository){
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
    }

    public void checkout(Object object){
        paymentGateway.charge(2D);
        orderRepository.save(object);
    }
}
