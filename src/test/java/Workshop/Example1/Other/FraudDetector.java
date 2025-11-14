package Workshop.Example1.Other;

import Workshop.Example2.Other.PaymentRequest;
import Workshop.Example2.Other.PaymentResult;
import net.bytebuddy.matcher.ElementMatcher;

public class FraudDetector {
    public Order screenOrder(ElementMatcher.Junction<Object> any) {
        return null;
    }

    public PaymentResult screenPayment(PaymentRequest request) {
        return null;
    }
}
