package com.gauthier.lab.springcore.scopes.session;

import com.gauthier.lab.springcore.scopes.request.ScopeController;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

@SpringBootTest(
        classes = SessionMainTest.SessionScopeTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class SessionMainTest extends TestCase {

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
    void sameSession() {

        var firstResponse = client.get()
                .uri("/session")
                .retrieve()
                .toEntity(String.class);

        String setCookie = firstResponse.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        System.out.println("Server Set-Coolie: " + setCookie);
        String sessionCookie = setCookie.split(";")[0];

        String second = client.get()
                .uri("/session")
                .header(HttpHeaders.COOKIE, sessionCookie)
                .retrieve()
                .body(String.class);

        String first = firstResponse.getBody();

        System.out.println("Request 1: " + first);
        System.out.println("Request 2: " + second);

        assertEquals(first, second);
        client.post()
                .uri("/session/invalidate")
                .header(HttpHeaders.COOKIE, sessionCookie)
                .retrieve()
                .toBodilessEntity();

    }
    @Test
    void differentSession() {

        var firstResponse = client.get()
                .uri("/session")
                .retrieve()
                .toEntity(String.class);


        String second = client.get()
                .uri("/session")
                .retrieve()
                .body(String.class);

        String first = firstResponse.getBody();

        System.out.println("Request 1: " + first);
        System.out.println("Request 2: " + second);

        assertNotSame(first, second);
    }

    @SpringBootConfiguration
    @EnableAutoConfiguration
    @Import({SessionController.class, SessionProbe.class})
    static class SessionScopeTestApplication {
    }
}