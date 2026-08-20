package com.gauthier.lab.springcore.scopes.prototype;

import com.gauthier.lab.springcore.scopes.singleton.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("3.2 - Prototype scope");
        System.out.println("================================");

        basicPrototype();
        initialization();
        destruction();
        prototypeInSingleton();
        objectProvider();

        System.out.println("================================");
        System.out.println("End of Prototype scope");
        System.out.println("================================");

    }

    private static void basicPrototype() {
        System.out.println("Exercise A - Basic Prototype");
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.springcore.scopes.prototype");
            context.refresh();
            var a = context.getBean(PrototypeProbe.class);
            var b = context.getBean(PrototypeProbe.class);
            System.out.println("a == b : ");
            System.out.println(a == b);
        } catch (Exception e) {
            System.out.println("Protoype main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void initialization() {
        System.out.println("Exercise B - Initialization");
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.springcore.scopes.prototype");
            context.refresh();
            context.getBean(PrototypeProbe.class);
            context.getBean(PrototypeProbe.class);
        } catch (Exception e) {
            System.out.println("Protoype main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void destruction() {
        System.out.println("Exercise C - Destruction");
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.springcore.scopes.prototype");
            context.refresh();
            context.getBean(PrototypeProbe.class);
            context.getBean(PrototypeProbe.class);
        } catch (Exception e) {
            System.out.println("Protoype main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void prototypeInSingleton() {
        System.out.println("Exercise D - Prototype Injected into Singleton");
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.springcore.scopes.prototype");
            context.refresh();
            ProbeService service = context.getBean(ProbeService.class);
            RequestProbe direct = context.getBean(RequestProbe.class);
            var a = service.probe();
            var b = service.probe();
            System.out.println("is (1)service.probe()  = (2)service.probe()");
            System.out.println(a == b);
            System.out.println("is service.probe()  = direct probe");
            System.out.println(a == direct);
        } catch (Exception e) {
            System.out.println("Protoype main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void objectProvider() {
        System.out.println("Exercise E - Object Provider");
        try (var context = new AnnotationConfigApplicationContext()) {
            context.scan("com.gauthier.lab.springcore.scopes.prototype");
            context.refresh();
            ProbeServiceObjectProvider service = context.getBean(ProbeServiceObjectProvider.class);
            var a = service.probe();
            var b = service.probe();
            System.out.println("is (1)service.probe()  = (2)service.probe()");
            System.out.println(a == b);
        } catch (Exception e) {
            System.out.println("Protoype main exception : " + e);
        }
        System.out.println("================================");
    }
}
