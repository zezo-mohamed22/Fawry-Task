import java.util.Date;

public class PerishableShippableProduct extends PerishableProduct implements Shippable {
    private double weight;

    public PerishableShippableProduct(int id, String name, double price, int quantity, Date expirationDate, double weight) {
        super(id, name, price, quantity, expirationDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() { return weight; }
}
