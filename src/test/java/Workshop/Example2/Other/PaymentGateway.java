package Workshop.Example2.Other;

public interface PaymentGateway {
    PaymentResponse charge(PaymentRequest request);
}
