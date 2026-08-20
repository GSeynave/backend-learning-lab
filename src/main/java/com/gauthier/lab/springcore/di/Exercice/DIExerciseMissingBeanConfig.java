package com.gauthier.lab.springcore.di.Exercice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIExerciseMissingBeanConfig {
    @Bean
    DIExercice.VatCalculator vatCalculator(){
        return new DIExercice.FrenchVatCalculator();
    }
    @Bean
    DIExercice.InvoiceService invoiceService(DIExercice.VatCalculator vatCalculator, DIExercice.InvoiceRepository invoiceRepository){
        return new DIExercice.InvoiceService(vatCalculator, invoiceRepository);
    }

}
