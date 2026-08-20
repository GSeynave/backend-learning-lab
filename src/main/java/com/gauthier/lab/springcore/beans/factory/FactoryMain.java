package com.gauthier.lab.springcore.beans.factory;

import com.gauthier.lab.springcore.beans.configuration.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class FactoryMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("2.5 - Factory");
        System.out.println("================================");

        identifyFactoryLayers();
        factoryMetadata();
        staticFactory();
        instanceFactory();
        factoryFailure();
        factoryBean();

        System.out.println("================================");
        System.out.println("End of Factory");
        System.out.println("================================");

    }

    private static void identifyFactoryLayers() {
        try (var context = new AnnotationConfigApplicationContext(HttpConfiguration.class)) {
            System.out.println("Exercise A - Identify factory layers");
            var beanDefinition = context.getBeanDefinition("httpClient");
            System.out.println("Factory class: " + beanDefinition.getFactoryBeanName());
            System.out.println("Factory method: " + beanDefinition.getFactoryMethodName());
        } catch (Exception e) {
            System.out.println("Factory main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void factoryMetadata() {
        try (var context = new AnnotationConfigApplicationContext(FactoryMetadataConfig.class)) {
            System.out.println("Exercise B - Factory metadata");
            var beanDefinition = context.getBeanDefinition("demoClient");
            System.out.println("Factory class: " + beanDefinition.getFactoryBeanName());
            System.out.println("Factory method: " + beanDefinition.getFactoryMethodName());
        } catch (Exception e) {
            System.out.println("Factory main exception : " + e);
        }
        System.out.println("================================");
    }
    private static void staticFactory() {
        System.out.println("Exercise c - Static Factory ");
        try (var context = new AnnotationConfigApplicationContext(SecureTokenConfiguration.class)) {
        } catch (Exception e) {
            System.out.println("Factory main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void instanceFactory() {
        System.out.println("Exercise D - Instance Factory ");
        try (var context = new AnnotationConfigApplicationContext(InstanceFactoryConfiguration.class)) {
        } catch (Exception e) {
            System.out.println("Factory main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void factoryFailure() {
        System.out.println("Exercise E - Factory Failure ");
        try (var context = new AnnotationConfigApplicationContext(FactoryFailureConfiguration.class)) {
        } catch (Exception e) {
            System.out.println("Factory main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void factoryBean() {
        System.out.println("Exercise F - Factory Bean ");
        try (var context = new AnnotationConfigApplicationContext(FactoryBeanConfiguration.class)) {
            var beanDefinition = context.getBeanDefinition("demoClient");
            System.out.println("Factory class: " + beanDefinition.getFactoryBeanName());
            System.out.println("Factory method: " + beanDefinition.getFactoryMethodName());
            var factory= context.getBean("&demoClient");
            System.out.println("Factory : " + factory.getClass());
        } catch (Exception e) {
            System.out.println("Factory main exception : " + e);
        }
        System.out.println("================================");
    }
}
