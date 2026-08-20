package com.gauthier.lab.springcore.di.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterAlphaService {

    private SetterBetaService betaService;

    public SetterAlphaService() {
    }

    @Autowired
    public void setBetaService(SetterBetaService betaService) {
        this.betaService = betaService;
    }
    public void hello(){
        System.out.println("Beta works");
    }
}
