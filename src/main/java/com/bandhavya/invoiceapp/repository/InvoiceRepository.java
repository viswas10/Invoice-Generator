package com.bandhavya.invoiceapp.repository;

import com.bandhavya.invoiceapp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface handles all the database operations for the Invoice entity.
 * Spring Data JPA automatically creates the necessary methods like save(),
 * findById(), findAll(), etc. You don't need to write any SQL code here.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}

