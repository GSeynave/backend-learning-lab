package com.gauthier.lab.springcore.scopes.application;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestClient;

@SpringBootTest(
        classes = ApplicationScopeTest.ApplicationScopeTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ApplicationScopeTest extends TestCase {

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
    void aplication() {

        var firstResponse = client.get()
                .uri("/application")
                .retrieve()
                .toEntity(String.class);

        var secondResponse = client.get()
                .uri("/application")
                .retrieve()
                .toEntity(String.class);

        var first = firstResponse.getBody();
        var second = secondResponse.getBody();

        System.out.println("first: " + first);
        System.out.println("second: " + second);
        assertEquals(first, second);

    }
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({ApplicationController.class, ApplicationScopeProbe.class})
    static class ApplicationScopeTestApplication {
    }
}