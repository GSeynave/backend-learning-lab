package com.gauthier.lab.di.constructorinjection;

public class InMemoryDocumentRepository implements DocumentRepository{


    @Override
    public void save(String content) {
        System.out.println("Saved: " + content);

    }
}
