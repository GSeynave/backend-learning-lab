package com.gauthier.lab.web.featureflag.lab.provider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "flagsmith")
public record FlagsmithProperties(String environmentKey, String userId, String baseUrl) {
}
