package com.gauthier.lab.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CookieController {

    @GetMapping("/set-cookie")
    ResponseEntity<String> setCookie() {
        var headers = new HttpHeaders();
        headers.add("Set-Cookie", "cookieName=sessionCookieValue; HttpOnly; Secure; SameSite=Strict");
        return new ResponseEntity<>("Cookie has been set in header", headers, HttpStatus.OK);
    }
}
