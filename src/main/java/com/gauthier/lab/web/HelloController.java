package com.gauthier.lab.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gauthier.lab.concurrency.ConcurrencyImpl;

@RestController
@RequestMapping("/hello")
class HelloController {

  @GetMapping
  String hello() {
    ConcurrencyImpl.checkTimeWithMultihread();
    return "Test complete!";
  }
}
