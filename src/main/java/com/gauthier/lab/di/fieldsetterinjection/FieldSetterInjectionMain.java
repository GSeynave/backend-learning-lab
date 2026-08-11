package com.gauthier.lab.di.fieldsetterinjection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FieldSetterInjectionMain {

    public void execute() {
        // Save constructor injection
        AlertSender sender = new ConsoleAlertSender();
        AuditRepository repository = new ConsoleAuditRepository();
        ConstructorSecurityService service = new ConstructorSecurityService(repository, sender);
        service.detect("New event to be audited");

        // Setter injection
        try {
            SetterSecurityService setterSecurityService = new SetterSecurityService();
            setterSecurityService.detect("FAILURE null dependencies");
        } catch (Exception e) {
            System.out.println("Setter injection no spring context : " + e);
        }
        SetterSecurityService manualSetterService = new SetterSecurityService();
        manualSetterService.setRepository(repository);
        manualSetterService.setSender(sender);
        manualSetterService.detect("Manual dependency set, working.");
        try {
            FieldSecurityService fieldSecurityService = new FieldSecurityService();
            fieldSecurityService.detect("FAILURE null dependencies");
        } catch (Exception e) {
            System.out.println("Field injection no spring context : " + e);
        }

        try (var context = new AnnotationConfigApplicationContext()) {
            context.registerBean(ConsoleAuditRepository.class);
            context.registerBean(ConsoleAlertSender.class);
            context.registerBean(ConstructorSecurityService.class);
            context.registerBean(SetterSecurityService.class);
            context.registerBean(FieldSecurityService.class);

            context.refresh();
            ConstructorSecurityService constructorSecurityService = context.getBean(ConstructorSecurityService.class);
            SetterSecurityService setterSecurityService = context.getBean(SetterSecurityService.class);
            FieldSecurityService fieldSecurityService = context.getBean(FieldSecurityService.class);

            constructorSecurityService.detect("Constructor event well instantiate via spring context");
            setterSecurityService.detect("Setter event well instantiate via spring context");
            fieldSecurityService.detect("Field event well instantiate via spring context");
        }
    }
}
