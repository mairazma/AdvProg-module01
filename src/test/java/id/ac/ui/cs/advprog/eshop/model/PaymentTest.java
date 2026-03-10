package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
        paymentData.put("Bank Central Asia", "BCA");
        paymentData.put("Bank Nasional Indonesia", "BNI");
    }

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals("WAITING_PAYMENT", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", "SUCCESS", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }


}