package com.gauthier.lab.springcore.di.constructorinjection;

public class InMemoryDocumentRepository implements DocumentRepository{


    @Override
    public void save(String content) {
        System.out.println("Saved: " + content);

    }
}
