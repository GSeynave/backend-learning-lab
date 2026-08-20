package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LazyReportEngine {
    public LazyReportEngine() {
        System.out.println("LazyReportEngine CREATED");
    }
}
