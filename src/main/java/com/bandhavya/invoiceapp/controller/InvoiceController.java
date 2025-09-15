package com.bandhavya.invoiceapp.controller;

import com.bandhavya.invoiceapp.model.Invoice;
import com.bandhavya.invoiceapp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller defines the REST API endpoints for your application.
 * It handles incoming web requests from the frontend.
 */
@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*") // Allows requests from any origin (e.g., your frontend)
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    /**
     * This method handles the HTTP POST request to save a new invoice.
     * The frontend JavaScript will call this endpoint.
     * @param invoice The invoice data from the webpage, converted from JSON into an Invoice object.
     * @return A response indicating success or failure.
     */
    @PostMapping("/save")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        try {
            // Before saving, ensure the bidirectional relationship is set
            if (invoice.getItems() != null) {
                invoice.getItems().forEach(item -> item.setInvoice(invoice));
            }
            
            Invoice savedInvoice = invoiceService.saveInvoice(invoice);
            return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            // For debugging, print the error to the console.
            e.printStackTrace();
            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

