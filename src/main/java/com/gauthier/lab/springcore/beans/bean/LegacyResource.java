package com.gauthier.lab.springcore.beans.bean;

public class LegacyResource {

    public LegacyResource() {
        System.out.println("CONSTRUCTOR");
    }

    public void open(){
        System.out.println("OPEN");
    }

    public void close(){
        System.out.println("CLOSE");
    }
}
