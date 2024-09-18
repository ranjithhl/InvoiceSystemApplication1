package com.example.InvoiceSystemApplication;

import com.example.InvoiceSystemApplication.model.Invoice;
import com.example.InvoiceSystemApplication.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
class InvoiceSystemApplicationTests {


    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testCreateInvoice() {
        Invoice invoice = invoiceService.createInvoice(199.99, LocalDate.of(2021, 9, 11));
        assertNotNull(invoice.getId());
        assertEquals(199.99, invoice.getAmount());
    }

    @Test
    public void testPaymentProcessing() {
        Invoice invoice = invoiceService.createInvoice(100.0, LocalDate.now());
        invoiceService.makePayment(invoice.getId(), 100.0);
        assertEquals("paid", invoice.getStatus());
    }

}
