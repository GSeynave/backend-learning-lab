package com.gauthier.lab.web.featureflag.lab.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab/flags")
public class FakeFeatureFlagController {

  @GetMapping("/{flagKey}")
  public FlagResponse getFlag(@PathVariable String flagKey, @RequestParam String userId, @RequestParam String country) {
    if (userId.equals("error")) {
      throw new RuntimeException("This is a fake controller, it should not be called in production");
    }
    if (flagKey.equals("new-checkout") && userId.equals("42") && country.equals("FR")) {
      return new FlagResponse(flagKey, "true");
    }
    if (flagKey.equals("checkout-variant") && userId.equals("42") && country.equals("FR")) {
      return new FlagResponse(flagKey, "variant-a");
    }
    return null;
  }

}
