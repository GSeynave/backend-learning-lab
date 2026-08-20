package com.gauthier.lab.springcore.scopes.request;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(
        classes = RequestMainTest.RequestScopeTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class RequestMainTest extends TestCase {

    @Autowired
    RequestScopeServiceB serviceB;
    @LocalServerPort
    int port;

    private RestClient client;

    @BeforeEach
    void setup() {
        client = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();
    }

    @Test
    void requestScopeCreatesOneInstancePerHttpRequest() {

        String first = client.get()
                .uri("/scope")
                .retrieve()
                .body(String.class);

        String second = client.get()
                .uri("/scope")
                .retrieve()
                .body(String.class);

        System.out.println("Request 1: " + first);
        System.out.println("Request 2: " + second);

        assertNotEquals(first, second);
    }

    @Test
    void requestScopeNoHttpRequest() {
        serviceB.id();
    }

    /**
     * A deliberately small Boot application for this lesson. It imports only
     * the controller and request-scoped bean being demonstrated.
     */
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({ScopeController.class, RequestScopeProbe.class, RequestScopeServiceA.class, RequestScopeServiceB.class})
    static class RequestScopeTestApplication {
    }

}
