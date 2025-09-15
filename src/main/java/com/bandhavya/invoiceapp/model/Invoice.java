package com.bandhavya.invoiceapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the 'invoices' table in your database.
 * Each instance of this class corresponds to a single row in that table.
 * The @Entity annotation tells Spring that this is a database model.
 */
@Entity
@Table(name = "invoices")
@Data // Lombok annotation to automatically generate getters, setters, etc.
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

    // This defines the one-to-many relationship with InvoiceItem.
    // When an invoice is saved, its items are saved too (cascade = CascadeType.ALL).
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

    // Explicitly adding the getter to resolve IDE compilation issues with Lombok.
    public List<InvoiceItem> getItems() {
        return items;
    }

    // Helper method to ensure the relationship is correctly set on both sides.
    public void setItems(List<InvoiceItem> items) {
        this.items = items;
        for (InvoiceItem item : items) {
            item.setInvoice(this);
        }
    }
}

