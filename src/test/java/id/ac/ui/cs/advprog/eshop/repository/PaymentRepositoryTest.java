package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> bankTransferData1 = new HashMap<>();
        bankTransferData1.put("Bank Central Asia", "BCA");
        bankTransferData1.put("Bank Nasional Indonesia", "BNI");

        Map<String, String> bankTransferData2 = new HashMap<>();
        bankTransferData2.put("Mandiri", "");
        bankTransferData2.put("Bank Syariah Indonesia", "BSI");

        payments.add(new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "BANK_TRANSFER", PaymentStatus.SUCCESS.getValue(), bankTransferData1));
        payments.add(new Payment("7f9e15bb-4b15-42f4-aebc-c3af385fb078", "BANK_TRANSFER", PaymentStatus.REJECTED.getValue(), bankTransferData2));
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Payment updated = new Payment(payment.getId(), payment.getMethod(),
                PaymentStatus.REJECTED.getValue(), payment.getPaymentData());
        paymentRepository.save(updated);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }
}