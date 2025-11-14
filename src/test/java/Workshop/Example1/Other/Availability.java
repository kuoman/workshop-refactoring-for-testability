package Workshop.Example1.Other;

public class Availability {
    private final boolean available;
    private final int quantity;

    public Availability(boolean available, int quantity) {
        this.available = available;
        this.quantity = quantity;
    }

    public static Availability available(int quantity) {
        return new Availability(true, quantity);
    }

    public static Availability unavailable() {
        return new Availability(false, 0);
    }

    public boolean isAvailable() {
        return available;
    }

    public int getQuantity() {
        return quantity;
    }
}
