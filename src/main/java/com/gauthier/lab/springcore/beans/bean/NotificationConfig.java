package com.gauthier.lab.springcore.beans.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationConfig {

    @Primary
    @Bean
    public MessageSender beanEmailSender(){
        return new EmailSender();
    }

    @Bean
    public SmsSender beanSmsSender(){
        return new SmsSender();
    }

    @Bean
    public NotificationService beanNotificationService(MessageSender sender){
        return new NotificationService(sender);
    }


}
