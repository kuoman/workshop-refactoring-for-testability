package Workshop.Example2.Other;

public class StripePaymentGateway implements PaymentGateway {

    public StripePaymentGateway(String apiKey) {

    }

    @Override
    public PaymentResponse charge(PaymentRequest request) {
        return null;
    }
}
