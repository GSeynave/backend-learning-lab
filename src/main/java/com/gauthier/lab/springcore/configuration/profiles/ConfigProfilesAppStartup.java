package com.gauthier.lab.springcore.configuration.profiles;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
        "com.gauthier.lab.springcore.configuration.profiles"
)
public class ConfigProfilesAppStartup {
}
