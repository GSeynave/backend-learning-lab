package com.gauthier.lab.springcore.beans.beancreation;

import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final NotReallyLazyReportEngine engine;

    public ReportService(NotReallyLazyReportEngine engine) {
        System.out.println("ReportService CREATED");
        this.engine = engine;
    }
}
