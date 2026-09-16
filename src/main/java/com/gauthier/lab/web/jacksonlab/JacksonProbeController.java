package com.gauthier.lab.web.jacksonlab;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/jackson")
public class JacksonProbeController {

    private final JsonMapper jsonMapper;

    public JacksonProbeController(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    JacksonHeroResponse echo(@RequestBody JacksonHeroRequest request) {
        System.out.println(jsonMapper.getClass());
        return new JacksonHeroResponse(
                request.name(),
                request.optionalLevel(),
                request.primitiveLevel(),
                request.role(),
                request.createdAt(),
                null,
                "secret"
        );
    }
}
