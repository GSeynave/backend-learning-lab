package com.gauthier.lab.di.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterBetaService {

    private SetterAlphaService alphaService;


    @Autowired
    public void setAlphaService(SetterAlphaService alphaService) {
        this.alphaService = alphaService;
    }

    public void hello(){
        System.out.println("Alpha works");
    }
}
