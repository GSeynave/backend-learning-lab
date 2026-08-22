package com.gauthier.lab.springcore.scopes.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ApplicationController {

    private final ApplicationScopeProbe probe;

    public ApplicationController(ApplicationScopeProbe probe ) {
        this.probe = probe;
    }

    @GetMapping("/application")
    public UUID scope(){
        return probe.id();
    }

    @GetMapping("/session")
    public UUID sessionScope(){
        return probe.id();
    }
}
