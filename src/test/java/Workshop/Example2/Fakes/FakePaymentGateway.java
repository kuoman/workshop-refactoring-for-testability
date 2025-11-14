package Workshop.Example2.Fakes;

import Workshop.Example2.Other.PaymentGateway;
import Workshop.Example2.Other.PaymentRequest;
import Workshop.Example2.Other.PaymentResponse;

public class FakePaymentGateway implements PaymentGateway {
    private String errorMessage = "Payment failed";
    private boolean shouldSucceed;

    public void configureSuccess() { shouldSucceed = true; }
    public void configureFailure(String error) {
        shouldSucceed = false;
        errorMessage = error;
    }

    @Override
    public PaymentResponse charge(PaymentRequest request) {
        return shouldSucceed ?
                PaymentResponse.success("tx_123") :
                PaymentResponse.failure(errorMessage);
    }
}
