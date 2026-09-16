package com.gauthier.lab.web.jacksonlab;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record JacksonHeroResponse(
        @JsonProperty("hero_name")
        String name,
        Integer optionalLevel,
        int primitiveLevel,
        HeroRole role,
        Instant createdAt,
        String title,
        @JsonIgnore
        String internalToken
) {
}
