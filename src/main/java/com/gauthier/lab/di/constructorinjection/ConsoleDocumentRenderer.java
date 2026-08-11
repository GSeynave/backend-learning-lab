package com.gauthier.lab.di.constructorinjection;

public class ConsoleDocumentRenderer implements DocumentRenderer{


    @Override
    public void render(String content) {
        System.out.println("Rendered: " +content);

    }
}
