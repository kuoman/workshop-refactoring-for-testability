package Workshop.Example2.Other;

public class PaymentResult {
    public static PaymentResult declined(String fraudRisk) {
        return null;
    }

    public static PaymentResult success(String transactionId) {
        return null;
    }

    public boolean isRisky() {
        return false;
    }

    public boolean isDeclined() {
        return false;
    }

    public String getReason() {
        return null;
    }
}
