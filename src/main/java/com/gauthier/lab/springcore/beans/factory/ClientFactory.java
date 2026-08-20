package com.gauthier.lab.springcore.beans.factory;

public class ClientFactory {

    public ClientFactory() {
        System.out.println("ClientFactory constructed");
    }

    public DemoClient createDemoClient(){
        System.out.println("createClient");
        return new DemoClient();
    }
}
