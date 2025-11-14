package Workshop.Example1.Other;

public class Order {
    private Money total;

    public Order(Money total) {
        this.total = total;
    }

    public Money getTotal() {
        return total;
    }
}
