package com.gauthier.lab.springcore.di.circulardependency;

public class AlphaService {

    private final BetaService betaService;

    public AlphaService(BetaService betaService) {
        this.betaService = betaService;
    }

    public void hello(){
        System.out.println("Alpha works");
    }
}
