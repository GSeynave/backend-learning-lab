package com.gauthier.lab.springcore.scopes.request;

import org.springframework.stereotype.Service;

@Service
public class RequestScopeServiceA {
    private final RequestScopeProbe probe;

    public RequestScopeServiceA(RequestScopeProbe probe) {
        this.probe = probe;
    }

    RequestScopeProbe probe(){
        return probe;
    }
}
