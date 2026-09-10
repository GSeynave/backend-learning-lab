package com.gauthier.lab.web.exception;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptions/heroes")
public class ExceptionController {

    @GetMapping("/{id}")
    public String getHero(@PathVariable String id) {
        throw new HeroNotFoundException(id);
    }
    @PostMapping()
    public String getHero(@RequestBody @Valid CreateHeroRequest request) {
        throw new HeroNameIsAlreadyUseException(request.name());
    }

    @GetMapping("/bug")
    public String getHero() {
        throw new NullPointerException();
    }
}
