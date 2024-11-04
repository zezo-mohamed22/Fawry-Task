// Cart.java
import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new LinkedHashMap<>();

    /**
     * Adds a product to the cart with the specified quantity.
     * Validates cumulative quantity against available stock.
     *
     * @param product  The product to add.
     * @param quantity The quantity to add.
     * @throws Exception If quantity is invalid or exceeds available stock.
     */
    public void addProduct(Product product, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Quantity must be greater than zero.");
        }

        int existingQuantityInCart = items.getOrDefault(product, 0);
        int totalRequestedQuantity = existingQuantityInCart + quantity;

        if (totalRequestedQuantity > product.getQuantity()) {
            throw new Exception("Only " + product.getQuantity() + " units of " + product.getName() + " are available. You have already added " + existingQuantityInCart + " units to your cart.");
        }

        items.put(product, totalRequestedQuantity);
    }

    /**
     * Calculates the subtotal of the cart.
     *
     * @return The subtotal amount.
     */
    public double calculateSubtotal() {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }

    /**
     * Retrieves a list of shippable items in the cart.
     *
     * @return List of shippable items.
     */
    public List<Shippable> getShippableItems() {
        List<Shippable> shippableItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            if (product instanceof Shippable) {
                for (int i = 0; i < entry.getValue(); i++) {
                    shippableItems.add((Shippable) product);
                }
            }
        }
        return shippableItems;
    }

    /**
     * Retrieves all items in the cart.
     *
     * @return Map of products and their quantities.
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * Checks if the cart is empty.
     *
     * @return True if empty, else false.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }
}
