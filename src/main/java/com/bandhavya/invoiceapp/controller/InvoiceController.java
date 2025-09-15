package com.bandhavya.invoiceapp.controller;

import com.bandhavya.invoiceapp.model.Invoice;
import com.bandhavya.invoiceapp.model.InvoiceItem;
import com.bandhavya.invoiceapp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/save")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        try {
            // **New:** Filter out any empty items sent from the frontend
            if (invoice.getItems() != null) {
                List<InvoiceItem> nonEmptyItems = invoice.getItems().stream()
                    .filter(item -> item.getParticulars() != null && !item.getParticulars().trim().isEmpty())
                    .collect(Collectors.toList());
                invoice.setItems(nonEmptyItems);
            }

            // **Crucial:** Set the bidirectional relationship before saving
            if (invoice.getItems() != null) {
                invoice.getItems().forEach(item -> item.setInvoice(invoice));
            }
            
            Invoice savedInvoice = invoiceService.saveInvoice(invoice);
            return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}