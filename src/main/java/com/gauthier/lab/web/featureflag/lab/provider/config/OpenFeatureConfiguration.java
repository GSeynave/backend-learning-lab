package com.gauthier.lab.web.featureflag.lab.provider.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.openfeature.contrib.providers.flagsmith.FlagsmithProvider;
import dev.openfeature.contrib.providers.flagsmith.FlagsmithProviderOptions;
import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.OpenFeatureAPI;

@Configuration
@EnableConfigurationProperties(FlagsmithProperties.class)
public class OpenFeatureConfiguration {

  @Bean
  public Client openFeatureClient(FlagsmithProperties properties) throws Exception {
    FlagsmithProvider provider = new FlagsmithProvider(FlagsmithProviderOptions.builder()
        .apiKey(properties.environmentKey())
        .baseUri(properties.baseUrl())
        .build());

    OpenFeatureAPI api = OpenFeatureAPI.getInstance();
    api.setProviderAndWait(provider);

    return api.getClient();
  }
}
