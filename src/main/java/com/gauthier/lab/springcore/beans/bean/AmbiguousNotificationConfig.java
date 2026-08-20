package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmbiguousNotificationConfig {

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
