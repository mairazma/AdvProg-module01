package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String orderId;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String orderId, String method, Map<String, String> paymentData) {
        this.id = id;
        this.orderId = orderId;
        this.method = method;
        this.paymentData = paymentData;
        this.status = PaymentStatus.WAITING_PAYMENT.getValue();
    }

    public Payment(String id, String orderId, String method, String status, Map<String, String> paymentData) {
        this(id, orderId, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}