package com.gauthier.lab.web.featureflag.lab.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.FeatureProvider;
import dev.openfeature.sdk.Metadata;
import dev.openfeature.sdk.ProviderEvaluation;
import dev.openfeature.sdk.Value;

@Component
public class OpenFeatureRemoteProvider implements FeatureProvider {

  private final RestClient restClient;

  public OpenFeatureRemoteProvider() {
    this.restClient = RestClient.builder()
        .baseUrl("http://localhost:8080/lab/flags")
        .build();
  }

  @Override
  public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue,
      EvaluationContext evaluationContext) {
    String userId = evaluationContext.getTargetingKey();
    var country = evaluationContext.getValue("country").asString();
    System.out.println("Evaluating flag: " + key + " default  : " + defaultValue + " for user: " + userId
        + " with country: " + country);

    FlagResponse flagResponse = restClient.get()
        .uri("/{flagKey}?userId={userId}&country={country}", key, userId, country)
        .retrieve()
        .body(FlagResponse.class);

    return ProviderEvaluation.<Boolean>builder()
        .value((Boolean) flagResponse.value())
        .build();
  }

  @Override
  public ProviderEvaluation<Double> getDoubleEvaluation(String arg0, Double arg1, EvaluationContext arg2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProviderEvaluation<Integer> getIntegerEvaluation(String arg0, Integer arg1, EvaluationContext arg2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Metadata getMetadata() {
    return () -> "OpenFeature Remote Provider";
  }

  @Override
  public ProviderEvaluation<Value> getObjectEvaluation(String arg0, Value arg1, EvaluationContext arg2) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue,
      EvaluationContext evaluationContext) {
    String userId = evaluationContext.getTargetingKey();
    var country = evaluationContext.getValue("country").asString();
    System.out.println("Evaluating flag: " + key + " default  : " + defaultValue + " for user: " + userId
        + " with country: " + country);

    FlagResponse flagResponse = restClient.get()
        .uri("/{flagKey}?userId={userId}&country={country}", key, userId, country)
        .retrieve()
        .body(FlagResponse.class);

    return ProviderEvaluation.<String>builder()
        .value((String) flagResponse.value())
        .build();
  }

}
