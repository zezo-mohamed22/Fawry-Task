import java.util.List;

public class Order {
    private int id;
    private Customer customer;
    private List<Product> products;
    private double totalAmount;

    public Order(int id, Customer customer, List<Product> products, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.products = products;
        this.totalAmount = totalAmount;
    }

    public int getId() { return id; }

    public Customer getCustomer() { return customer; }

    public double getTotalAmount() { return totalAmount; }
}
