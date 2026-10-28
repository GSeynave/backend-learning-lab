package com.gauthier.lab.web.featureflag.lab.provider;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gauthier.lab.web.featureflag.lab.provider.config.FlagsmithProperties;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.ImmutableContext;
import dev.openfeature.sdk.Value;

@Service
public class CheckoutCalculatorService {

  private final LegacyCheckoutCalculator legacyCheckoutCalculator;
  private final NewCheckoutCalculator newCheckoutCalculator;
  private final Client featureFlagClient;
  private final FlagsmithProperties flagsmithProperties;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public CheckoutCalculatorService(LegacyCheckoutCalculator legacyCheckoutCalculator,
      NewCheckoutCalculator newCheckoutCalculator,
      Client featureFlagClient,
      FlagsmithProperties flagsmithProperties) {
    this.legacyCheckoutCalculator = legacyCheckoutCalculator;
    this.newCheckoutCalculator = newCheckoutCalculator;
    this.featureFlagClient = featureFlagClient;
    this.flagsmithProperties = flagsmithProperties;
  }

  public List<String> getTopics() {
    var value = featureFlagClient.getObjectValue("allowed-topics", null);
    System.out.println(value);
    System.out.println(value.getClass());
    System.out.println(value.asString());
    try {
      return objectMapper.readValue(value.asString(), new TypeReference<List<String>>() {
      });

    } catch (Exception e) {
      throw new RuntimeException("Failed to parse allowed-topics flag value", e);
    }

  }

  public String calculateCheckout(String userId, String country) {
    Map<String, Value> contextMap = Map.of("country", Value.objectToValue(country), "userId",
        Value.objectToValue(userId));
    EvaluationContext evaluationContext = new ImmutableContext(flagsmithProperties.userId(), contextMap);

    boolean enabled = featureFlagClient.getBooleanValue("new-checkout", false, evaluationContext);
    var details = featureFlagClient.getBooleanDetails("new-checkout", false, evaluationContext);

    System.out.println("Flag details: " + details);

    return enabled ? newCheckoutCalculator.calculateCheckout() : legacyCheckoutCalculator.calculateCheckout();
  }

  public String calculateDiscount(String userId, String country) {
    Map<String, Value> contextMap = Map.of("country", Value.objectToValue(country));
    EvaluationContext evaluationContext = new ImmutableContext(userId, contextMap);
    boolean enabled = featureFlagClient.getBooleanValue("new-discount", false, evaluationContext);

    return enabled ? newCheckoutCalculator.calculateCheckout() : legacyCheckoutCalculator.calculateCheckout();
  }

  public String checkoutVariant(String userId, String country) {
    Map<String, Value> contextMap = Map.of("country", Value.objectToValue(country));
    EvaluationContext evaluationContext = new ImmutableContext(userId, contextMap);
    return featureFlagClient.getStringValue("checkout-variant", "none", evaluationContext);
  }
}
