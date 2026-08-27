package com.gauthier.lab.springcore.configuration.configurationproperties;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        "com.gauthier.lab.springcore.configuration.configurationproperties"
)
@EnableConfigurationProperties(AcademyProperties.class)
public class ConfigurationPropertiesAppStartup {
}
