package com.gauthier.lab.web.featureflag.lab.provider;

import org.springframework.stereotype.Component;

@Component
public class NewCheckoutCalculator {

  public String calculateCheckout() {
    return "new-checkout";
  }
}
