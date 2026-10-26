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
  public FlagResponse getFlag(@PathVariable String flagKey, @RequestParam String userId) {
    boolean enabled = flagKey.equals("new-checkout")
        && userId.equals("42");
    return new FlagResponse(flagKey, enabled);
  }

}
