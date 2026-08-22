package com.gauthier.lab.springcore.scopes.application;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@ApplicationScope
@Component
public class ApplicationScopeProbe {
    private final UUID uuid =UUID.randomUUID();

    public UUID id() {
        return uuid;
    }
}
