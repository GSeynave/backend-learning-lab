package com.gauthier.lab;

import com.gauthier.lab.springcore.beans.bean.BeanMain;
import com.gauthier.lab.springcore.beans.beancreation.BeanCreationMain;
import com.gauthier.lab.springcore.beans.beanlifecycle.BeanLifecycleMain;
import com.gauthier.lab.springcore.beans.componentscanning.ComponentScanningMain;
import com.gauthier.lab.springcore.beans.configuration.ConfigurationMain;
import com.gauthier.lab.springcore.beans.factory.FactoryMain;
import com.gauthier.lab.springcore.configuration.applicationyml.ConfigurationYAMLMain;
import com.gauthier.lab.springcore.configuration.configurationproperties.ConfigurationPropertiesMain;
import com.gauthier.lab.springcore.configuration.environmentvar.EnvironmentVariableMain;
import com.gauthier.lab.springcore.configuration.profiles.ProfilesMain;
import com.gauthier.lab.springcore.di.beanresolution.BeanResolutionMain;
import com.gauthier.lab.springcore.di.circulardependency.CircularDependencyMain;
import com.gauthier.lab.springcore.di.constructorinjection.ConstructorInjectionMain;
import com.gauthier.lab.springcore.di.fieldsetterinjection.FieldSetterInjectionMain;
import com.gauthier.lab.springcore.scopes.prototype.PrototypeMain;
import com.gauthier.lab.springcore.scopes.singleton.SingletonMain;
import com.gauthier.lab.springcore.springboot.applciationrun.starters.ApplicationRunMain;
import com.gauthier.lab.springcore.springboot.autoconfig.AutoConfigMain;
import com.gauthier.lab.springcore.springboot.starters.StartersMain;
import com.gauthier.lab.springcore.springboot.tomcat.TomcatMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The executable web application.
 *
 * <p>Spring Core exercises deliberately create their own small application
 * contexts. They are not part of this application's component scan, otherwise
 * their alternative beans would all be registered together.</p>
 */
@SpringBootApplication(scanBasePackages = "com.gauthier.lab.web")
public class App {
    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);

        beanModule(false);
        scopes(false);
        applicationProperties(false);
        springBoot(args,true);
    }

    private static void springBoot(String[] args, Boolean enable) {
        if (!enable) {
            return;
        }
        /*
        AutoConfigMain autoConfigMain = new AutoConfigMain();
        autoConfigMain.execute();
         */

//        StartersMain startersMain = new StartersMain();
//        startersMain.execute();

        /*
        ApplicationRunMain applicationRunMain = new ApplicationRunMain();
        applicationRunMain.execute(args);
         */

        TomcatMain tomcatMain = new TomcatMain();
        tomcatMain.execute();
    }

    private static void applicationProperties(Boolean enable) {
        if (!enable) {
            return;
        }
        ConfigurationYAMLMain configurationYAMLMain = new ConfigurationYAMLMain();
        configurationYAMLMain.execute();

        ProfilesMain profilesMain = new ProfilesMain();
        profilesMain.execute();

        EnvironmentVariableMain environmentVariableMain = new EnvironmentVariableMain();
        environmentVariableMain.execute();

        ConfigurationPropertiesMain configurationPropertiesMain = new ConfigurationPropertiesMain();
        configurationPropertiesMain.execute();
    }

    private static void scopes(Boolean enable) {
        if (!enable) {
            return;
        }
        SingletonMain singletonMain = new SingletonMain();
        singletonMain.execute();

        PrototypeMain prototypeMain = new PrototypeMain();
        prototypeMain.execute();
    }

    private static void beanModule(Boolean enable) {
        if (!enable) {
            return;
        }
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

        ComponentScanningMain componentScanningMain = new ComponentScanningMain();
        componentScanningMain.execute();

        BeanCreationMain beanCreationMain = new BeanCreationMain();
        beanCreationMain.execute();

        BeanLifecycleMain beanLifecycleMain = new BeanLifecycleMain();
        beanLifecycleMain.execute();

        BeanMain beanMain = new BeanMain();
        beanMain.execute();

        ConfigurationMain configurationMain = new ConfigurationMain();
        configurationMain.execute();

        FactoryMain factoryMain = new FactoryMain();
        factoryMain.execute();
    }
}
