package com.gauthier.lab.web.featureflag.lab.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lab")
public class CheckoutController {

  private final CheckoutCalculatorService checkoutCalculatorService;

  public CheckoutController(final CheckoutCalculatorService checkoutCalculatorService) {
    this.checkoutCalculatorService = checkoutCalculatorService;
  }

  @GetMapping("/checkout")
  public String getCheckout(@RequestParam String userId) {
    return checkoutCalculatorService.calculateCheckout(userId);
  }

  @GetMapping("/discount")
  public String getDiscount(@RequestParam String userId) {
    return checkoutCalculatorService.calculateDiscount(userId);
  }
}
