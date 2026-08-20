package com.gauthier.lab.springcore.beans.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("2.5 - @Configuration");
        System.out.println("================================");

        fullConfiguration();
        fullInterBeanCall();
        noProxyFullInterBeanCall();
        parameterInjectionNoProxyFullInterBeanCall();
        manualConfiguration();
        lite();

        System.out.println("================================");
        System.out.println("End of @Configuration");
        System.out.println("================================");

    }

    private static void fullConfiguration() {
        try (var context = new AnnotationConfigApplicationContext(FullConfiguration.class)) {
            System.out.println("Exercise A - Observe full Configuration Enhancement");
            var configuration = context.getBean(FullConfiguration.class);
            System.out.println(configuration.getClass().getName());
        } catch (Exception e) {
            System.out.println("Configuration main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void fullInterBeanCall() {
        try (var context = new AnnotationConfigApplicationContext(InterBeanFullConfiguration.class)) {
            System.out.println("Exercise B - Full Inter Bean Call");
            DemoRepository repoFromContext = context.getBean(DemoRepository.class);
            DemoService service = context.getBean(DemoService.class);
            System.out.println(repoFromContext == service.repository());

        } catch (Exception e) {
            System.out.println("Configuration main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void noProxyFullInterBeanCall() {
        try (var context = new AnnotationConfigApplicationContext(NoProxyInterBeanFullConfiguration.class)) {
            System.out.println("Exercise C - No Proxy Full Inter Bean Call");
            DemoRepository repoFromContext = context.getBean(DemoRepository.class);
            DemoService service = context.getBean(DemoService.class);
            System.out.println(repoFromContext == service.repository());

        } catch (Exception e) {
            System.out.println("Configuration main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void parameterInjectionNoProxyFullInterBeanCall() {
        try (var context = new AnnotationConfigApplicationContext(ParameterInjectionNoProxyInterBeanFullConfiguration.class)) {
            System.out.println("Exercise D - Parameter Injection No Proxy Full Inter Bean Call");
            DemoRepository repoFromContext = context.getBean(DemoRepository.class);
            DemoService service = context.getBean(DemoService.class);
            System.out.println(repoFromContext == service.repository());

        } catch (Exception e) {
            System.out.println("Configuration main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void manualConfiguration() {
        try  {
            System.out.println("Exercise E - Manual configuration");
            ManualConfiguration manualConfiguration = new ManualConfiguration();
            DemoRepository first = manualConfiguration.demoRepository();
            DemoRepository second = manualConfiguration.demoRepository();
            System.out.println(first == second);

        } catch (Exception e) {
            System.out.println("Configuration main exception : " + e);
        }
        System.out.println("================================");
    }

    private static void lite() {
        try  (var context = new AnnotationConfigApplicationContext()){
            System.out.println("Exercise F - Lite");
            context.registerBean(LiteFactory.class);
            context.refresh();

            LiteFactory.LiteRepository repository = context.getBean(LiteFactory.LiteRepository.class);
            LiteFactory.LiteService service = context.getBean(LiteFactory.LiteService.class);
            System.out.println(repository == service.repository());

        } catch (Exception e) {
            System.out.println("Configuration main exception : " + e);
        }
        System.out.println("================================");
    }
}
