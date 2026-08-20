package com.gauthier.lab.springcore.scopes.prototype;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class ProbeServiceObjectProvider {

    private final ObjectProvider<RequestProbe> provider;

    public ProbeServiceObjectProvider(ObjectProvider<RequestProbe> provider) {
        this.provider = provider;
    }

    RequestProbe probe() {
        return provider.getObject();
    }
}
