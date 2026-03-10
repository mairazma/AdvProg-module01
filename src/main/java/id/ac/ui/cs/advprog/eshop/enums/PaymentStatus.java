package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    REJECTED("REJECTED"),
    SUCCESS("SUCCESS");

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static boolean contains(String param) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}