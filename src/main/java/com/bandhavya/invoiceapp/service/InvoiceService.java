package com.bandhavya.invoiceapp.service;

import com.bandhavya.invoiceapp.model.Invoice;
import com.bandhavya.invoiceapp.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service layer contains the business logic. It acts as a bridge
 * between the controller and the repository.
 */
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * Saves a complete invoice with all its items.
     * The @Transactional annotation ensures that the entire operation
     * (saving the invoice and all its items) succeeds or fails together.
     * @param invoice The invoice object to be saved.
     * @return The saved invoice object with its database-generated ID.
     */
    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}

