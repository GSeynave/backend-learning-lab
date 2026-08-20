package com.gauthier.lab.springcore.scopes.request;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RequestScopeServiceB {
    private final RequestScopeProbe probe;

    public RequestScopeServiceB(RequestScopeProbe probe) {
        this.probe = probe;
    }

    RequestScopeProbe probe() {
        return probe;
    }

    UUID id() {
        return probe.id();
    }
}
