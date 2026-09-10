package com.gauthier.lab.web.exception;

import java.util.UUID;

public class HeroNotFoundException extends RuntimeException{
    private final String heroId;
    public HeroNotFoundException(String heroId) {
        super("Hero not found with ID: " + heroId);
        this.heroId = heroId;
    }

}
