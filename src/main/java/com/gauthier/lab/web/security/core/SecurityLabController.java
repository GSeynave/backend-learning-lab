package com.gauthier.lab.web.security.core;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security-lab")
public class SecurityLabController {

  AuthenticationManager authenticationManager;

  @GetMapping("/hello")
  public String hello(Authentication authentication) {
    return """
        authenticationClass=%s
        name=%s
        authenticated=%s
        authorities=%s
        principalClass=%s
            """.formatted(
        authentication.getClass().getName(),
        authentication.getName(),
        authentication.isAuthenticated(),
        authentication.getAuthorities(),
        authentication.getPrincipal().getClass().getName());
  }

  @GetMapping("/public")
  public String publicEndpoint() {
    return "This is a public endpoint";
  }

  @GetMapping("/admin")
  public String adminEndpoint() {
    return "This is an admin endpoint";
  }
}
