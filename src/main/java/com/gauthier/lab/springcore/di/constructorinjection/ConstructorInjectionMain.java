package com.gauthier.lab.springcore.di.constructorinjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConstructorInjectionMain {

    public void execute() {

        manualInjection();
        springConstructorInjection();
    }


    private void manualInjection() {
        DocumentRepository repository = new InMemoryDocumentRepository();
        DocumentRenderer documentRenderer = new ConsoleDocumentRenderer();

        DocumentPublishingService service = new DocumentPublishingService(
                repository, documentRenderer);
        service.publish("Hello manual DI");

    }

    private void springConstructorInjection(){
        try (var context = new AnnotationConfigApplicationContext()){
            // Inject bean in spring context, to let him resolve dependencies by itself when required.
            context.registerBean(InMemoryDocumentRepository.class);
            /* Intentionally missing a bean for injection
            context.registerBean(ConsoleDocumentRenderer.class);
             */
            context.registerBean(DocumentPublishingService.class);

            context.refresh();

            DocumentPublishingService service = context.getBean(DocumentPublishingService.class);
            service.publish("Hello Spring");

        } catch (Exception e){
            System.out.println("Spring Constructor Injection Error : "+  e);
        }
    }
}
