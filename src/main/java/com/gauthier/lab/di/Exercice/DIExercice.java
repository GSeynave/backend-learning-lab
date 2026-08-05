package com.gauthier.lab.di.Exercice;

import com.gauthier.lab.di.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DIExercice {

    /* Class given to be refactor using a DI implementation
    public class InvoiceListener {
        private final VatCalculator vatCalculator =
                new FrenchVatCalculator();

        private final InvoiceRepository repository =
                new FileInvoiceRepository("./invoices");

        public Invoice createInvoice(
                String customerId,
                Money subtotal
        ) {
            Money vat = vatCalculator.calculate(subtotal);

            Invoice invoice =
                    new Invoice(
                            customerId,
                            subtotal,
                            vat
                    );

            repository.save(invoice);

            return invoice;
        }
    }
     */

    public void runExercice() {
        // Simulate main as i use this repo as a lab.
        //manualDI();
        //configurationBeanDI();

        substitutionDI();
        missingBeanException();
    }
    private void missingBeanException(){
        try(var context = new AnnotationConfigApplicationContext(DIExerciseMissingBeanConfig.class)){
            InvoiceService invoiceService = context.getBean(InvoiceService.class);
            invoiceService.createInvoice("Customer-1", new Money(10D));
        }
    }


    private void substitutionDI(){
        InvoiceRepository invoiceRepository = new FileInvoiceRepository("./invoices");
        VatCalculator vatCalculator = new DefaultVatCalculator();
        var invoiceService = new InvoiceService(vatCalculator, invoiceRepository);
        var invoice = invoiceService.createInvoice("Customer-Substitute-1", new Money(99999D));
        System.out.println(invoice.toString());
    }

    public static class DefaultVatCalculator implements VatCalculator{
        @Override
        public Money calculate(Money subtotal){
            return subtotal;
        }


    }

    private void configurationBeanDI(){
        try(var context = new AnnotationConfigApplicationContext(DIExerciseConfig.class)){
            InvoiceService invoiceService = context.getBean(InvoiceService.class);
            invoiceService.createInvoice("Customer-1", new Money(10D));
        }
    }

    private void manualDI() {
        VatCalculator vatCalculator = new FrenchVatCalculator();
        InvoiceRepository invoiceRepository = new FileInvoiceRepository("./invoices");

        var invoiceService = new InvoiceService(vatCalculator, invoiceRepository);
        invoiceService.createInvoice("Customer-1", new Money(10D));
    }

    public static class InvoiceService {
        private final VatCalculator vatCalculator;
        private final InvoiceRepository invoiceRepository;

        public InvoiceService(VatCalculator vatCalculator, InvoiceRepository invoiceRepository) {
            this.vatCalculator = vatCalculator;
            this.invoiceRepository = invoiceRepository;
        }

        public Invoice createInvoice(String customerId, Money subTotal){
            Money vat = vatCalculator.calculate(subTotal);

            Invoice invoice = new Invoice(customerId, subTotal.value, vat.value);

            invoiceRepository.save(invoice);
            return invoice;
        }
    }

    public interface VatCalculator {
        Money calculate(Money subTotal);
    }

    public interface InvoiceRepository {
        void save(Invoice invoice);
    }

    public static class FrenchVatCalculator implements VatCalculator {
        @Override
        public Money calculate(Money subTotal) {
            return subTotal;
        }
    }

    public static class FileInvoiceRepository implements InvoiceRepository {

        private String path;

        public FileInvoiceRepository(String path) {
            this.path = path;
        }

        @Override
        public void save(Invoice invoice) {
            System.out.println(invoice.toString());
        }
    }

    public class Money {
        Double value;

        public Money(Double amount) {
            this.value = amount;
        }
    }

    public static class Invoice {
        String customerId;
        Double subTotal;
        Double vat;

        public Invoice(String customerId, Double subTotal, Double vat) {
            this.customerId = customerId;
            this.subTotal = subTotal;
            this.vat = vat;
        }

        @Override
        public String toString() {
            return "Invoice for customer + " + customerId + ", subtotal : +" + subTotal + ", vat rate : " + vat;

        }
    }
}
