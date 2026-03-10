package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    Order order;
    List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testAddPaymentBankTransferSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Bank Central Asia", "BCA");
        paymentData.put("Bank Nasional Indonesia", "BNI");

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals("BANK_TRANSFER", result.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentBankTransferRejectedEmptyBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Mandiri", "");
        paymentData.put("Bank Syariah Indonesia", "BSI");

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentBankTransferRejectedEmptyReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Bank Central Asia", "BCA");
        paymentData.put("Mandiri", "");

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testAddPaymentBankTransferRejectedNullBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("Mandiri", null);
        paymentData.put("Bank Syariah Indonesia", "BSI");

        Payment result = paymentService.addPayment(order, "BANK_TRANSFER", paymentData);

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
    }
}