package com.gauthier.lab.di.beanresolution;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanResolutionMain {

    public void execute() {
        System.out.println("Ambiguous execution");
        ambiguousBeans();

        System.out.println("Force primary execution");
        forcePrimaryBean();

        System.out.println("Qualifier execution");
        qualifierBean();

        System.out.println("Inject All");
        injectAll();

        System.out.println("Bean Name Resolution");
        beanNameResolution();
    }

    private static void ambiguousBeans() {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.registerBean("emailSender", EmailMessageSender.class);
            context.registerBean("smsSender", SmsMessageSender.class);

            context.registerBean(NotificationService.class);

            context.refresh();
            NotificationService notificationService = context.getBean(NotificationService.class);
            notificationService.notify("Primary set to a bean as a fix purpose");
        } catch (Exception e){
            System.out.println("Error ambiguous bean, "+e);
        }
    }

    private static void forcePrimaryBean() {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.registerBean("emailSender", PrimaryEmailMessageSender.class);
            context.registerBean("smsSender", SmsMessageSender.class);

            context.registerBean(NotificationService.class);

            context.refresh();
        } catch (Exception e){
            System.out.println("Error ambiguous bean, "+e);
        }
    }
    private static void qualifierBean() {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.registerBean("emailSender", EmailMessageSender.class);
            context.registerBean("smsSender", SmsMessageSender.class);

            context.registerBean(QualifiedNotificationService.class);

            context.refresh();
            QualifiedNotificationService notificationService = context.getBean(QualifiedNotificationService.class);
            notificationService.notify("Used Qualifier as filter metadata for bean resolution");
        } catch (Exception e){
            System.out.println("Error ambiguous bean, "+e);
        }
    }

    private static void injectAll() {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.registerBean("emailSender", EmailMessageSender.class);
            context.registerBean("smsSender", SmsMessageSender.class);

            context.registerBean(BroadcastService.class);

            context.refresh();
            BroadcastService broadcastService = context.getBean(BroadcastService.class);
            broadcastService.broadcast("Message broadcast to all");
        } catch (Exception e){
            System.out.println("Error ambiguous bean, "+e);
        }
    }
    private static void beanNameResolution() {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.registerBean("emailSender", EmailMessageSender.class);
            context.registerBean("smsSender", SmsMessageSender.class);

            context.registerBean(NamedRsolutionNotificationService.class);

            context.refresh();
            NamedRsolutionNotificationService notificationService = context.getBean(NamedRsolutionNotificationService.class);
            notificationService.notify("Message for auto resolve sms sender bean");
        } catch (Exception e){
            System.out.println("Error ambiguous bean, "+e);
        }
    }
}
