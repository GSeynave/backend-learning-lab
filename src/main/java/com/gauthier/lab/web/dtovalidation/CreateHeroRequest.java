package com.gauthier.lab.web.dtovalidation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateHeroRequest(
        @NotBlank
        @Size(max = 20)
        String name,

        @Min(1)
        int level,

        @NotNull
        @Valid
        EquipmentRequest equipment
) {
}
