package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    REJECTED("REJECTED"),
    SUCCESS("SUCCESS");

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }


}