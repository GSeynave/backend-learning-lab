package com.gauthier.lab.web.featureflag.lab.provider;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CheckoutCalculatorService {

  private final LegacyCheckoutCalculator legacyCheckoutCalculator;
  private final NewCheckoutCalculator newCheckoutCalculator;
  private final RestClient restClient;

  public CheckoutCalculatorService(LegacyCheckoutCalculator legacyCheckoutCalculator,
      NewCheckoutCalculator newCheckoutCalculator) {
    this.legacyCheckoutCalculator = legacyCheckoutCalculator;
    this.newCheckoutCalculator = newCheckoutCalculator;
    this.restClient = RestClient.builder()
        .baseUrl("http://localhost:8080/lab/flags")
        .build();
  }

  public String calculateCheckout(String userId) {
    FlagResponse flag = restClient.get()
        .uri("/new-checkout?userId={userId}", userId)
        .retrieve()
        .body(FlagResponse.class);

    return flag.enabled() ? newCheckoutCalculator.calculateCheckout() : legacyCheckoutCalculator.calculateCheckout();
  }

  public String calculateDiscount(String userId) {
    FlagResponse flag = restClient.get()
        .uri("/new-discount?userId={userId}", userId)
        .retrieve()
        .body(FlagResponse.class);

    return flag.enabled() ? newCheckoutCalculator.calculateCheckout() : legacyCheckoutCalculator.calculateCheckout();
  }
}
