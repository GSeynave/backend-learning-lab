package com.gauthier.lab.springcore.scopes.session;

import com.gauthier.lab.springcore.scopes.prototype.PrototypeProbe;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SessionMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("3.2 - Session scope");
        System.out.println("================================");

        sameSession();

        System.out.println("================================");
        System.out.println("End of Session scope");
        System.out.println("================================");

    }

    private static void sameSession() {
        System.out.println("Exercise A - Same Session");
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

}
