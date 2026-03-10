package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PaymentService paymentService;

    Payment payment;

    @BeforeEach
    void setUp() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF001");

        payment = new Payment("pay-001", "order-001", "BANK_TRANSFER", "SUCCESS", paymentData);
    }

    @Test
    void testPaymentDetailForm() throws Exception {
        mockMvc.perform(get("/payment/detail"))
                .andExpect(status().isOk())
                .andExpect(view().name("PaymentDetail"));
    }

    @Test
    void testPaymentDetailFound() throws Exception {
        when(paymentService.getPayment("pay-001")).thenReturn(payment);

        mockMvc.perform(get("/payment/detail/pay-001"))
                .andExpect(status().isOk())
                .andExpect(view().name("PaymentDetail"))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void testPaymentAdminList() throws Exception {
        when(paymentService.getAllPayments()).thenReturn(List.of(payment));

        mockMvc.perform(get("/payment/admin/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("PaymentAdminList"))
                .andExpect(model().attributeExists("payments"));
    }

    @Test
    void testPaymentAdminDetail() throws Exception {
        when(paymentService.getPayment("pay-001")).thenReturn(payment);

        mockMvc.perform(get("/payment/admin/detail/pay-001"))
                .andExpect(status().isOk())
                .andExpect(view().name("PaymentAdminDetail"))
                .andExpect(model().attributeExists("payment"));
    }

    @Test
    void testSetPaymentStatus() throws Exception {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF001");
        Payment updated = new Payment("pay-001", "order-001", "BANK_TRANSFER", "REJECTED", paymentData);

        when(paymentService.getPayment("pay-001")).thenReturn(payment);
        when(paymentService.setStatus(any(Payment.class), eq("REJECTED"))).thenReturn(updated);

        mockMvc.perform(post("/payment/admin/set-status/pay-001")
                        .param("status", "REJECTED"))
                .andExpect(status().isOk())
                .andExpect(view().name("PaymentAdminDetail"))
                .andExpect(model().attributeExists("payment"));
    }
}