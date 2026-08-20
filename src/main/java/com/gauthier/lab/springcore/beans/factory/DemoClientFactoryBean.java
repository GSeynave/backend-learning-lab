package com.gauthier.lab.springcore.beans.factory;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.FactoryBean;

public class DemoClientFactoryBean implements FactoryBean<DemoClient> {
    @Override
    public @Nullable DemoClient getObject() throws Exception {
        System.out.println("getObject()");
        return new DemoClient();

    }

    @Override
    public @Nullable Class<?> getObjectType() {
        return DemoClient.class;
    }
}
