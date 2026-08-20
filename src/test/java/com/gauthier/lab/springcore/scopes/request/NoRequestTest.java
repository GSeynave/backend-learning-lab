package com.gauthier.lab.springcore.scopes.request;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoRequestTest {

    @Autowired
    RequestScopeServiceB serviceB;

    @Test
    void requestScopeNoHttpRequest() {

        serviceB.id();
    }
}
