package com.gauthier.lab.web.negotiation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/negotiation")
public class NegotiationController {

  @GetMapping(path = "/hero", produces = "application/json")
  HeroDto josn() {
    return new HeroDto("Superman");
  }

  @PostMapping(path = "/hero", consumes = "application/json", produces = "application/json")
  HeroDto echo(@RequestBody HeroDto hero) {
    return hero;
  }

  @GetMapping(path = "/multi", produces = { "application/json" })
  HeroDto multiJson() {
    System.out.println("JSON Handler executed");
    return new HeroDto("Batman");
  }

  @GetMapping(path = "/multi", produces = { "plain/text" })
  HeroDto multiText() {
    System.out.println("TEXT Handler executed");
    return new HeroDto("Batman");
  }

  @GetMapping(path = "/vendor", produces = { "application/vnd.hero+json" })
  HeroDto vendorJson() {
    System.out.println("VENDOR Handler executed");
    return new HeroDto("Batman");
  }

  @GetMapping(path = "/parameter", produces = { "application/json" })
  HeroDto parameterJson() {
    System.out.println("PARAMETER Handler executed");
    return new HeroDto("Batman");
  }

  public record HeroDto(String name) {
  }

}
