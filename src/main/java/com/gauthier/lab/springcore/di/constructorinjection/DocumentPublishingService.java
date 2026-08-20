package com.gauthier.lab.springcore.di.constructorinjection;

import java.util.Objects;

public class DocumentPublishingService {

    private final DocumentRepository repository;
    private final DocumentRenderer renderer;

    /*
    public DocumentPublishingService() {
        // Adding a no args constructor to make spring init it to force a breaking behavior
        this.repository = null;
        this.renderer = null;
        System.out.println("NO ARGS CONSTRUCTOR");
    }
     */

    public DocumentPublishingService(
            DocumentRepository repository,
            DocumentRenderer renderer
    ) {
        this.repository =
                Objects.requireNonNull(repository);

        this.renderer =
                Objects.requireNonNull(renderer);
    }

    public void publish(String content) {
        repository.save(content);
        renderer.render(content);
    }
}
