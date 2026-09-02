package com.gauthier.lab.springcore.springboot.applciationrun.starters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationRunConfiguration {
    @Bean
    ApplicationRunRunnerProbe applicationRunRunnerProbe(){
        return new ApplicationRunRunnerProbe();
    }

    @Bean
    ApplicationRunLifecycleProbe applicationRunLifecycleProbe(){
        return new ApplicationRunLifecycleProbe();
    }
}
