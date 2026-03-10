package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.OrderService;
import id.ac.ui.cs.advprog.eshop.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @MockBean
    PaymentService paymentService;

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
    void testCreateOrderPage() throws Exception {
        mockMvc.perform(get("/order/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateOrder"));
    }

    @Test
    void testHistoryPage() throws Exception {
        mockMvc.perform(get("/order/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("OrderHistory"));
    }

    @Test
    void testHistoryResult() throws Exception {
        List<Order> orders = List.of(order);
        when(orderService.findAllByAuthor("Safira Sudrajat")).thenReturn(orders);

        mockMvc.perform(post("/order/history")
                        .param("author", "Safira Sudrajat"))
                .andExpect(status().isOk())
                .andExpect(view().name("OrderHistory"))
                .andExpect(model().attributeExists("orders"))
                .andExpect(model().attribute("author", "Safira Sudrajat"));
    }

    @Test
    void testPayOrderPage() throws Exception {
        when(orderService.findById(order.getId())).thenReturn(order);

        mockMvc.perform(get("/order/pay/" + order.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("PayOrder"))
                .andExpect(model().attributeExists("order"));
    }

    @Test
    void testPayOrder() throws Exception {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "REF001");

        Payment payment = new Payment("pay-001", order.getId(), "BANK_TRANSFER", "SUCCESS", paymentData);

        when(orderService.findById(order.getId())).thenReturn(order);
        when(paymentService.addPayment(any(Order.class), anyString(), anyMap())).thenReturn(payment);

        mockMvc.perform(post("/order/pay/" + order.getId())
                        .param("method", "BANK_TRANSFER")
                        .param("bankName", "BCA")
                        .param("referenceCode", "REF001"))
                .andExpect(status().isOk())
                .andExpect(view().name("PayOrderResult"))
                .andExpect(model().attributeExists("paymentId"));
    }
}