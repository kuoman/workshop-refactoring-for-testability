package Workshop.Example2;

import Workshop.Example2.Fakes.FakeFraudDetector;
import Workshop.Example2.Fakes.FakePaymentGateway;
import Workshop.Example2.Fakes.SpyAuditLogger;
import Workshop.Example2.Other.PaymentRequest;
import Workshop.Example2.Other.PaymentResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentProcessorTests {
    @Test
    void shouldDeclinePaymentWhenFraudDetected() {
        // Arrange: Configure test doubles for specific scenario
        FakeFraudDetector fraudDetector = new FakeFraudDetector();
        fraudDetector.configureRiskyResult();

        SpyAuditLogger auditLogger = new SpyAuditLogger();

        PaymentProcessor processor = new PaymentProcessor(
                new FakePaymentGateway(), fraudDetector, auditLogger
        );

        // Act: Execute the business scenario
        PaymentResult result = processor.processPayment(createPaymentRequest());

        // Assert: Verify business rule was applied
        assertThat(result.isDeclined()).isTrue();
        assertThat(result.getReason()).isEqualTo("Fraud risk");
        assertThat(auditLogger.wasMessageLogged("Fraud detected: customer_123")).isTrue();
    }

    private PaymentRequest createPaymentRequest() {
        return null;
    }
}
