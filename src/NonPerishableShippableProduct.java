public class NonPerishableShippableProduct extends NonPerishableProduct implements Shippable {
    private double weight;

    public NonPerishableShippableProduct(int id, String name, double price, int quantity, double weight) {
        super(id, name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() { return weight; }
}
