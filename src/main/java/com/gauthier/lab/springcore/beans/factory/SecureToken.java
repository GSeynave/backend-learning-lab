package com.gauthier.lab.springcore.beans.factory;

public class SecureToken {
    public SecureToken() {
        System.out.println("SecureToken constructor");
    }

    public static SecureToken create(){
        System.out.println("SecureToken static factory");
        return new SecureToken();
    }
}
