package com.gauthier.lab.web.featureflag.lab.provider;

import org.springframework.stereotype.Component;

@Component
public class LegacyCheckoutCalculator {

  public String calculateCheckout() {
    return "legacy-checkout";
  }
}
