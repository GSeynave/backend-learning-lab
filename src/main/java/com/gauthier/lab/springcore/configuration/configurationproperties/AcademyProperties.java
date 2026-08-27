package com.gauthier.lab.springcore.configuration.configurationproperties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.net.URI;
import java.time.Duration;

@ConfigurationProperties(prefix = "academy.config")
@Validated
public record AcademyProperties(
        @NotBlank String name,
        @NotNull Duration lessonDuration,
        @Min(1) int maxStudents,
        @NotNull URI documentationUrl
) {
}
