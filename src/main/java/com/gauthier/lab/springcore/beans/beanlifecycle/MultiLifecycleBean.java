package com.gauthier.lab.springcore.beans.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MultiLifecycleBean implements InitializingBean, DisposableBean {

    public MultiLifecycleBean() {
        System.out.println("constructor");
    }

    @PostConstruct
    void postConstruct(){
        System.out.println("@PostConstruct");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }
    @PreDestroy
    void preDestroy(){
        System.out.println("@PreDestroy");
    }
    @Override
    public void destroy() throws Exception {

        System.out.println("DisposableBean.destroy");
    }

}
