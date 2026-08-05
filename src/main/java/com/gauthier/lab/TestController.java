package com.gauthier.lab;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gauthier.lab.concurrency.ConcurrencyImpl;

@RestController
@RequestMapping("hello")
class TestController {

  @GetMapping()
  public String test() {
    ConcurrencyImpl.checkTimeWithMultihread();
    return "Test complete!";
  }

}
