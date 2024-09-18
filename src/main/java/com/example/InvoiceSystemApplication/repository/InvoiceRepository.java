package com.example.InvoiceSystemApplication.repository;

import com.example.InvoiceSystemApplication.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByStatus(String status);
    List<Invoice> findAllByDueDateBeforeAndStatus(LocalDate date, String status);
}
