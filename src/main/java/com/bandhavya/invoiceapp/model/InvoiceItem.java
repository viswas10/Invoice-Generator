package com.bandhavya.invoiceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "invoice_items")
@Data
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String particulars;
    
    @Column(name = "hsn_code")
    private String hsnCode;
    
    private Double quantity;
    private Double rate;
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    // The incorrect, empty setInvoice method has been removed.
    // Lombok will now provide the correct functionality.
}