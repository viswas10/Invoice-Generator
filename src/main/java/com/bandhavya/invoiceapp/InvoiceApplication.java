package com.bandhavya.invoiceapp; // Corrected package

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Add this line to explicitly tell Spring where to find your components
@ComponentScan(basePackages = "com.bandhavya.invoiceapp")
public class InvoiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvoiceApplication.class, args);
    }
}