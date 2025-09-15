package com.bandhavya.invoiceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * This class represents the 'invoice_items' table in your database.
 * It holds the data for each line item in an invoice.
 */
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

    // This defines the many-to-one relationship back to the Invoice.
    // Many items can belong to one invoice.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @JsonIgnore // Prevents issues with infinite loops when converting to JSON.
    private Invoice invoice;

	public void setInvoice(Invoice invoice2) {
		// TODO Auto-generated method stub
		
	}
}

