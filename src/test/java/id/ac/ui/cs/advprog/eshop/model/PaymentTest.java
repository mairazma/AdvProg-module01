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
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF001");
    }

    @Test
    void testCreatePaymentSuccess() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", paymentData);

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getOrderId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", "SUCCESS", paymentData);

        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                    "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", "MEOW", paymentData);
        });
    }

    @Test
    void testCreatePaymentEmptyBankName() {
        paymentData.put("", "Mandiri");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", "SUCCESS", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentNullBankName() {
        paymentData.put(null, "Mandiri");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", "SUCCESS", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentEmptyReferenceCode() {
        paymentData.put("Mandiri", "");
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", "SUCCESS", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentNullReferenceCode() {
        paymentData.put("Mandiri", null);
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                "13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", "SUCCESS", paymentData);
        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
    }
}