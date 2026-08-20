package com.gauthier.lab.web;

import com.gauthier.lab.concurrency.ConcurrencyImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
class HelloController {

    @GetMapping
    String hello() {
        ConcurrencyImpl.checkTimeWithMultihread();
        return "Test complete!";
    }
}
