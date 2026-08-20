package com.gauthier.lab.springcore.di.Exercice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIExerciseMissingBeanConfig {
    @Bean
    DIExercice.VatCalculator missingVatCalculator(){
        return new DIExercice.FrenchVatCalculator();
    }
    @Bean
    DIExercice.InvoiceService missingInvoiceService(DIExercice.VatCalculator vatCalculator, DIExercice.InvoiceRepository invoiceRepository){
        return new DIExercice.InvoiceService(vatCalculator, invoiceRepository);
    }

}
