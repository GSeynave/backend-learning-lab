package com.gauthier.lab.springcore.beans.bean;

public class TimeProvider {

    public TimeProvider() {
        System.out.println("TimeProvider constructor");
    }

    public long now(){
        return System.currentTimeMillis();
    }
}
