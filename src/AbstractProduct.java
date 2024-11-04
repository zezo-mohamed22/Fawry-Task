public abstract class AbstractProduct implements Product {
    protected int id;
    protected String name;
    protected double price;
    protected int quantity;

    public AbstractProduct(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int getId() { return id; }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public int getQuantity() { return quantity; }

    @Override
    public void reduceQuantity(int qty) { this.quantity -= qty; }

    @Override
    public boolean isAvailable(int qty) { return quantity >= qty; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
