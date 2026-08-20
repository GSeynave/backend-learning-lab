package com.gauthier.lab.springcore.scopes.prototype;

import org.springframework.stereotype.Service;

@Service
public class ProbeService {

    private final RequestProbe probe;

    public ProbeService(RequestProbe probe) {
        this.probe = probe;
    }

    RequestProbe probe(){
        return probe;
    }
}
