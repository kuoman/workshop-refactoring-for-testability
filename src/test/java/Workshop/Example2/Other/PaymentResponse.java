package Workshop.Example2.Other;

public class PaymentResponse {
    public static PaymentResponse success(String tx123) {
        return null;
    }

    public static PaymentResponse failure(String errorMessage) {
        return null;
    }

    public String getTransactionId() {
        return null;
    }

    public boolean isSuccessful() {
        return false;
    }

    public String getErrorMessage() {
        return null;
    }
}
