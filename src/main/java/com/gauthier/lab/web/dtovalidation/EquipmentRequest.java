package com.gauthier.lab.web.dtovalidation;

import jakarta.validation.constraints.NotBlank;

public record EquipmentRequest(
        @NotBlank
        String name
) {
}
