package com.gauthier.lab.di.circulardependency;

public class BetaService {

    private final AlphaService alphaService;

    public BetaService(AlphaService alphaService) {
        this.alphaService = alphaService;
    }
    public void hello(){
        System.out.println("Beta works");
    }
}
