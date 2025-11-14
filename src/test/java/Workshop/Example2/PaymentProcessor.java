package Workshop.Example2;

import Workshop.Example1.Other.FraudDetector;
import Workshop.Example2.Fakes.FakeFraudDetector;
import Workshop.Example2.Fakes.FakePaymentGateway;
import Workshop.Example2.Fakes.SpyAuditLogger;
import Workshop.Example2.Other.*;

public class PaymentProcessor {
    private static final String API_KEY = "someKey";
    private static final String FRAUD_API_URL = "someUrl";
    private static final String DB_CONNECTION = "dbConnectionString";

    public PaymentProcessor(FakePaymentGateway fakePaymentGateway, FakeFraudDetector fraudDetector, SpyAuditLogger auditLogger) {
    }

    public PaymentResult processPayment(PaymentRequest request) {
        // Direct dependencies embedded in method
        PaymentGateway gateway = new StripePaymentGateway(API_KEY);
        FraudDetector detector = new ExternalFraudService(FRAUD_API_URL);
        AuditLogger logger = new DatabaseAuditLogger(DB_CONNECTION);

        // Business logic mixed with infrastructure
        if (detector.screenPayment(request).isRisky()) {
            logger.log("Fraud detected: " + request.getCustomerId());
            return PaymentResult.declined("Fraud risk");
        }

        PaymentResponse response = gateway.charge(request);
        logger.log("Payment processed: " + response.getTransactionId());

        // The actual business rule (buried in infrastructure noise):
        return response.isSuccessful() ?
                PaymentResult.success(response.getTransactionId()) :
                PaymentResult.declined(response.getErrorMessage());
    }
}
