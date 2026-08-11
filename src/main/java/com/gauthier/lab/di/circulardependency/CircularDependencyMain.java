package com.gauthier.lab.di.circulardependency;

import com.gauthier.lab.di.fieldsetterinjection.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CircularDependencyMain {

    public void execute() {
        // circular dependency
        try(var context = new AnnotationConfigApplicationContext()){
            context.registerBean(AlphaService.class);
            context.registerBean(BetaService.class);

            context.refresh();
        } catch (Exception e){
            System.out.println("Circular Dependency Error : " + e);
        }


        //AlphaService alphaService = new AlphaService(new BetaService(.. ?))

        try(var context = new AnnotationConfigApplicationContext()){
            context.registerBean(SetterAlphaService.class);
            context.registerBean(SetterBetaService.class);

            context.refresh();

            context.getBean(SetterAlphaService.class).hello();
            context.getBean(SetterBetaService.class).hello();

        } catch (Exception e){
            System.out.println("Circular Dependency Error : " + e);
        }
    }
}
