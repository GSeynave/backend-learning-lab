package com.gauthier.lab.springcore.scopes.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SessionController {

    private final SessionProbe sessionProbe;

    public SessionController(SessionProbe sessionProbe) {
        this.sessionProbe = sessionProbe;
    }

    @GetMapping("/session")
    public UUID sessionScope(){
        return sessionProbe.id();
    }

    @PostMapping("/session/invalidate")
    void invalidate(HttpSession session){
        session.invalidate();
    }
}
