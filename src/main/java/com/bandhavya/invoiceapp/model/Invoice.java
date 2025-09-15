package com.bandhavya.invoiceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address")
    private String customerAddress;
    
    @Column(name = "customer_gstin")
    private String customerGstin;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "dc_number")
    private String dcNumber;
    
    @Column(name = "dc_date")
    private LocalDate dcDate;

    @Column(name = "po_number")
    private String poNumber;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<InvoiceItem> items = new ArrayList<>();

    private Double subtotal;
    private Double cgstRate;
    private Double sgstRate;
    private Double igstRate;
    private Double cgstAmount;
    private Double sgstAmount;
    private Double igstAmount;
    private Double grandTotal;
    
    @Column(name = "amount_in_words")
    private String amountInWords;

    // The manual setItems and getItems methods have been removed.
    // Lombok will handle them, and the controller will manage the relationship.
}