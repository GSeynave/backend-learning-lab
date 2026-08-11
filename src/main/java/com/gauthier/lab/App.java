package com.gauthier.lab;

import com.gauthier.lab.di.Exercice.DIExercice;
import com.gauthier.lab.di.beanresolution.BeanResolutionMain;
import com.gauthier.lab.di.circulardependency.CircularDependencyMain;
import com.gauthier.lab.di.constructorinjection.ConstructorInjectionMain;
import com.gauthier.lab.di.*;
import com.gauthier.lab.di.fieldsetterinjection.FieldSetterInjectionMain;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);
        //DIMain diMain = new DIMain();
        //diMain.execute();


        // Constructor injection
        ConstructorInjectionMain constructorInjectionMain = new ConstructorInjectionMain();
        constructorInjectionMain.execute();

        // Setter / Field injection
        FieldSetterInjectionMain setterInjectionMain = new FieldSetterInjectionMain();
        setterInjectionMain.execute();

        CircularDependencyMain circularDependencyMain = new CircularDependencyMain();
        circularDependencyMain.execute();


        BeanResolutionMain beanResolutionMain = new BeanResolutionMain();
        beanResolutionMain.execute();



    }

}
