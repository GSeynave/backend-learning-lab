package com.gauthier.lab.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mapping")
class MappingProbeController {

    @GetMapping("/{id}")
    String variable(@PathVariable String id) {
        return "Variable ID: " + id;
    }

    @GetMapping("/current")
    String current(){
        return "Current mapping";
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    HeroDto create(@RequestBody HeroDto heroDto) {
        return heroDto;
    }


}

record HeroDto(String name, String superPower) {}
