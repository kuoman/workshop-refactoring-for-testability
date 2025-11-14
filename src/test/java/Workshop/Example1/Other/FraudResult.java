package Workshop.Example1.Other;

public class FraudResult {
    private final boolean clean;
    private final String reason;

    public FraudResult(boolean clean, String reason) {
        this.clean = clean;
        this.reason = reason;
    }

    public static FraudResult clean() {
        return new FraudResult(true, "Clean");
    }

    public static FraudResult flagged(String reason) {
        return new FraudResult(false, reason);
    }

    public boolean isClean() {
        return clean;
    }

    public String getReason() {
        return reason;
    }
}
