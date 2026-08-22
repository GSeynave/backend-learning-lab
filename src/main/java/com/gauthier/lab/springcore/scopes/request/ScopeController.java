package com.gauthier.lab.springcore.scopes.request;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ScopeController {

    private final RequestScopeProbe probe;
    private final RequestScopeServiceA serviceA;
    private final RequestScopeServiceB serviceB;

    public ScopeController(RequestScopeProbe probe, RequestScopeServiceA serviceA, RequestScopeServiceB serviceB) {
        this.probe = probe;
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

    @GetMapping("/scope")
    public UUID scope(){
        System.out.println("probe service A = probe Service B : ");
        System.out.println(serviceA.probe().equals(serviceB.probe()));

        System.out.println("Request scope proxy : " + probe.getClass().getName());
        return probe.id();
    }

    @GetMapping("/session")
    public UUID sessionScope(){
        return probe.id();
    }
}
