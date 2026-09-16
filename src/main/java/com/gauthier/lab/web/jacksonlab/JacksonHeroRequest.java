package com.gauthier.lab.web.jacksonlab;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record JacksonHeroRequest(
        @JsonProperty("hero_name")
        @JsonAlias("heroName")
        String name,
        Integer optionalLevel,
        int primitiveLevel,
        HeroRole role,
        Instant createdAt
) {
}
