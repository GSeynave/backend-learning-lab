package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationConfig {

    @Primary
    @Bean
    public MessageSender emailSender(){
        return new EmailSender();
    }

    @Bean
    public SmsSender smsSender(){
        return new SmsSender();
    }

    @Bean
    public NotificationService notificationService(MessageSender sender){
        return new NotificationService(sender);
    }


}
