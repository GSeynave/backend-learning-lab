package com.gauthier.lab.web.exception;

import jakarta.validation.constraints.NotBlank;

public record CreateHeroRequest(
        @NotBlank String name
) {
}
