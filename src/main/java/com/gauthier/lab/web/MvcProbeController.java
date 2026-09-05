package com.gauthier.lab.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MvcProbeController {
    @GetMapping("/mvc/{id}")
    public String getMvcProbe(@PathVariable String id) {
        return "MVC Probe ID: " + id;
    }
}

