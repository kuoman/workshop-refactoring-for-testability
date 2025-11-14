package Workshop.Example1.Other;

import net.bytebuddy.matcher.ElementMatcher;

public class PaymentGateway {
    public boolean authorize(ElementMatcher.Junction<Object> any) {
        return false;
    }
}
