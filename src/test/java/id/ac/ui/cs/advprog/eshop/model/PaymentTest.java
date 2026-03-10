package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", "SUCCESS", paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyBankName() {
        paymentData.put("", "Mandiri");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentNullBankName() {
        paymentData.put(null, "Mandiri");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyReferenceCode() {
        paymentData.put("Mandiri", "");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentNullReferenceCode() {
        paymentData.put("Mandiri", null);
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }
}