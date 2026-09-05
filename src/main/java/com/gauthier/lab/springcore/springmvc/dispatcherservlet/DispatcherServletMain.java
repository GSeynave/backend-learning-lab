package com.gauthier.lab.springcore.springmvc.dispatcherservlet;

import com.gauthier.lab.springcore.configuration.configurationproperties.ConfigurationPropertiesAppStartup;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class DispatcherServletMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("Dispatcher Servlet");
        System.out.println("================================");

        springMvc();

        System.out.println("================================");
        System.out.println("End of Dispatcher Servlet");
        System.out.println("================================");

    }

    private static void springMvc() {
        System.out.println("Dispatcher Servlet");
        // curl http://localhost:{port}/mvc/1
        // See step of the dispatcher servlet in the logs
        System.out.println("================================");
    }
}
