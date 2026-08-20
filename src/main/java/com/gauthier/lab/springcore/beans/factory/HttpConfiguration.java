package com.gauthier.lab.springcore.beans.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class HttpConfiguration {

    @Bean
    public HttpClient httpClient(){
        return HttpClient.newHttpClient();
    }
}
