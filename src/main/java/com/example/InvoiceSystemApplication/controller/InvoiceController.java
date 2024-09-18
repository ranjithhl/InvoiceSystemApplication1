package com.example.InvoiceSystemApplication.controller;

import com.example.InvoiceSystemApplication.model.Invoice;
import com.example.InvoiceSystemApplication.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> createInvoice(@RequestBody Map<String, Object> request) {
        double amount = (double) request.get("amount");
        LocalDate dueDate = LocalDate.parse((String) request.get("due_date"));
        Invoice invoice = invoiceService.createInvoice(amount, dueDate);
        Map<String, Long> response = Collections.singletonMap("id", invoice.getId());
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<Invoice> makePayment(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        Invoice updatedInvoice = invoiceService.makePayment(id, amount);
        return ResponseEntity.ok(updatedInvoice);
    }

    @PostMapping("/process-overdue")
    public ResponseEntity<Void> processOverdueInvoices(@RequestBody Map<String, Object> request) {
        double lateFee = (double) request.get("late_fee");
        int overdueDays = (int) request.get("overdue_days");
        invoiceService.processOverdueInvoices(lateFee, overdueDays);
        return ResponseEntity.ok().build();
    }
}
