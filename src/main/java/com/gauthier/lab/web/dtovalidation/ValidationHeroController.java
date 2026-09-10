package com.gauthier.lab.web.dtovalidation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation/heroes")
class ValidationHeroController {

    @PostMapping("/{slot}")
    ResponseEntity<Void> create(
            @PathVariable
            @Min(1)
            int slot,

            @Valid
            @RequestBody
            CreateHeroRequest request
    ) {
        System.out.println("CONTROLLER EXECUTED");
        return ResponseEntity.noContent().build();
    }
}
