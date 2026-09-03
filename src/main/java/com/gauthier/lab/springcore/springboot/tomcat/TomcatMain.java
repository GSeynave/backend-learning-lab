package com.gauthier.lab.springcore.springboot.tomcat;

import com.gauthier.lab.App;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.server.context.WebServerApplicationContext;
import org.springframework.boot.web.server.servlet.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class TomcatMain {

    public void execute() {

        System.out.println("================================");
        System.out.println("5.5 - Tomcat");
        System.out.println("================================");

        tomcat();

        System.out.println("================================");
        System.out.println("End of Tomcat");
        System.out.println("================================");

    }

    private static void tomcat() {
        // make it a web application context
        SpringApplication application = new SpringApplication(App.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        ConfigurableApplicationContext context = application.run();
        System.out.println(context.getBean(ServletWebServerFactory.class)
                .getClass()
                .getName());
        System.out.println(context.getClass().getName());
        var webContext = (WebServerApplicationContext)  context;
        System.out.println(webContext.getWebServer().getPort());

        System.out.println("================================");
    }
}
