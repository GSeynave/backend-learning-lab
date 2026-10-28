package com.gauthier.lab.web.featureflag.lab.provider;

import java.util.List;

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
  public String getCheckout(@RequestParam String userId, @RequestParam String country) {
    return checkoutCalculatorService.calculateCheckout(userId, country);
  }

  @GetMapping("/discount")
  public String getDiscount(@RequestParam String userId, @RequestParam String country) {
    return checkoutCalculatorService.calculateDiscount(userId, country);
  }

  @GetMapping("/variant")
  public String getCheckoutVariant(@RequestParam String userId, @RequestParam String country) {
    return checkoutCalculatorService.checkoutVariant(userId, country);
  }

  @GetMapping("/topics")
  public List<String> getTopics() {
    return checkoutCalculatorService.getTopics();
  }
}
