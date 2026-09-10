package com.gauthier.lab.web.exception;

public class HeroNameIsAlreadyUseException extends RuntimeException{

    private final String name;

    public HeroNameIsAlreadyUseException(String name) {
        super("Hero name is already in use: " + name);
        this.name = name;
    }
}
