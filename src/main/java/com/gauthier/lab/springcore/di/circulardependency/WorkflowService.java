package com.gauthier.lab.springcore.di.circulardependency;

public class WorkflowService {

    private final AlphaService alphaService;
    private final BetaService betaService;

    public WorkflowService(AlphaService alphaService, BetaService betaService) {
        this.alphaService = alphaService;
        this.betaService = betaService;
    }

    public void hello() {
        // Dumb process assumming aplha needed beta and vice versa.
        // just to demonstrate that an orchestrator is a right answer to circular dependency
        // that often is from an architectural cause.
        alphaService.hello();
        betaService.hello();
    }
}
