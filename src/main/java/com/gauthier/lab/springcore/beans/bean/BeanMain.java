package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanMain {

    public void execute(){

        System.out.println("================================");
        System.out.println("2.4 - @Bean");
        System.out.println("================================");

        beanConfig();
        methodParameterDI();
        nestedDI();
        beanNames();
        lifecycle();
        ambiguousExplicitBeans();

        System.out.println("================================");
        System.out.println("End of @Bean");
        System.out.println("================================");

    }

    private static void beanConfig() {
        try ( var context = new AnnotationConfigApplicationContext(BeanConfig.class)){
            System.out.println("Exercise A - Basic @Bean");
            var timeProvider = context.getBean(TimeProvider.class);
            System.out.println(timeProvider.now());
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void methodParameterDI() {
        try ( var context = new AnnotationConfigApplicationContext(AuditConfig.class)){
            System.out.println("Exercise B - Method Parameter DI");
            var service = context.getBean(BeanAuditService.class);
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void nestedDI() {
        try ( var context = new AnnotationConfigApplicationContext(NestedAuditConfig.class)){
            System.out.println("Exercise C - Nested DI");
            var service = context.getBean(BeanAuditService.class);
            var repository = context.getBean(AuditRepository.class);
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void beanNames() {
        try ( var context = new AnnotationConfigApplicationContext(AuditConfig.class)){
            System.out.println("Exercise D - Bean Names");
            System.out.println("Contains auditRepository: " + context.containsBean("auditRepository"));
            System.out.println("Contains mainAuditRepository: " + context.containsBean("mainAuditRepository"));
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void lifecycle() {
        try ( var context = new AnnotationConfigApplicationContext(LegacyConfig.class)){
            System.out.println("Exercise E - Lifecycle");
            context.getBean("legacyResource");
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void ambiguousExplicitBeans() {
        try ( var context = new AnnotationConfigApplicationContext(AmbiguousNotificationConfig.class)){
            System.out.println("Exercise F - 1 - Ambiguous explicit Bean");
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        try ( var context = new AnnotationConfigApplicationContext(NotificationConfig.class)){
            System.out.println("Exercise F - 2 - Ambiguous explicit Bean -> Resolved with @Primary");
        } catch (Exception e){
            System.out.println("Bean  main exception : " + e);
        }
        System.out.println("================================");
    }
}
