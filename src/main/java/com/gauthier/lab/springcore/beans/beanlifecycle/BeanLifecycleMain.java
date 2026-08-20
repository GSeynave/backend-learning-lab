package com.gauthier.lab.springcore.beans.beanlifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanLifecycleMain {

    public void execute(){

        System.out.println("================================");
        System.out.println("2.3 - Bean Lifecycle");
        System.out.println("================================");
        beanLifecycleOrder();
        beanMultiLifecycleOrder();
        beanInitializationFailure();
        manualObjectCreation();
        System.out.println("End of Bean Lifecycle");
        System.out.println("================================");

    }

    private static void beanLifecycleOrder() {
        try ( var context = new AnnotationConfigApplicationContext()){
            System.out.println("Exercise A - Bean Lifecycle order");
            context.scan("com.gauthier.lab.spring.beans.beanlifecycle");
            context.refresh();
            var service = context.getBean(LifecycleService.class);
            service.execute();
        } catch (Exception e){
            System.out.println("Bean lifecycle main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void beanMultiLifecycleOrder() {
        try ( var context = new AnnotationConfigApplicationContext()){
            System.out.println("Exercise B - Bean Multi Lifecycle order");
            context.registerBean(MultiLifecycleBean.class);
            context.refresh();
        } catch (Exception e){
            System.out.println("Bean lifecycle main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void beanInitializationFailure(){
        try(var context = new AnnotationConfigApplicationContext()){
            System.out.println("Exercise C - Bean failure at initialization call backs");
            context.registerBean(BrokenInitializationBean.class);
            context.refresh();
        } catch (Exception ignored){}
        System.out.println("================================");
    }

    private static void manualObjectCreation(){
        try(var context = new AnnotationConfigApplicationContext()){
            System.out.println("Exercise D - Manual object creation");
            LifecycleService lifecycleService = new LifecycleService();
        } catch (Exception ignored){}
        System.out.println("================================");
    }

}
