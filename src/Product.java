public interface Product {
    int getId();
    String getName();
    double getPrice();
    int getQuantity();
    void reduceQuantity(int qty);
    boolean isAvailable(int qty);
}
