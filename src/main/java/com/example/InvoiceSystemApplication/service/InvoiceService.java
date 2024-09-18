package com.example.InvoiceSystemApplication.service;


import com.example.InvoiceSystemApplication.model.Invoice;
import com.example.InvoiceSystemApplication.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(double amount, LocalDate dueDate) {
        Invoice invoice = new Invoice(amount, dueDate);
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice makePayment(Long invoiceId, double amount) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow();
        double remainingAmount = invoice.getAmount() - invoice.getPaidAmount();
        if (amount >= remainingAmount) {
            invoice.setPaidAmount(invoice.getAmount());
            invoice.setStatus("paid");
        } else {
            invoice.setPaidAmount(invoice.getPaidAmount() + amount);
        }
        return invoiceRepository.save(invoice);
    }

    public void processOverdueInvoices(double lateFee, int overdueDays) {
        LocalDate currentDate = LocalDate.now().minusDays(overdueDays);
        List<Invoice> overdueInvoices = invoiceRepository.findAllByDueDateBeforeAndStatus(currentDate, "pending");

        for (Invoice invoice : overdueInvoices) {
            if (invoice.getPaidAmount() > 0) {
                double remainingAmount = invoice.getAmount() - invoice.getPaidAmount();
                Invoice newInvoice = new Invoice(remainingAmount + lateFee, LocalDate.now().plusDays(overdueDays));
                invoiceRepository.save(newInvoice);
                invoice.setStatus("paid");
            } else {
                Invoice newInvoice = new Invoice(invoice.getAmount() + lateFee, LocalDate.now().plusDays(overdueDays));
                invoiceRepository.save(newInvoice);
                invoice.setStatus("void");
            }
            invoiceRepository.save(invoice);
        }
    }
}
