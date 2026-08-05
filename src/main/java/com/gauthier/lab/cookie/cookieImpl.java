package com.gauthier.lab.cookie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CookieImpl {

  @GetMapping("/set-cookie")
  public ResponseEntity<String> getCookie() {
    // Code that would verify a token, then generate a session cookie and set it in
    // ...

    var headers = new org.springframework.http.HttpHeaders();
    headers.add("Set-Cookie", "cookieName=sessionCookieValue; httpOnly; Secure; SameSite=Strict");
    return new ResponseEntity<>("Cookie has been set in header", headers, HttpStatus.OK);
  }

}
