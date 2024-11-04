import java.util.*;

public class Checkout {
    public static void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) {
            throw new Exception("Cart is empty.");
        }

        double subtotal = cart.calculateSubtotal();
        double shippingFees = 0;

        List<Shippable> shippableItems = cart.getShippableItems();

        // Check for perishable items expiration and stock availability
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Perishable) {
                Perishable perishableProduct = (Perishable) product;
                if (perishableProduct.isExpired()) {
                    throw new Exception("Product " + product.getName() + " is expired.");
                }
            }

            if (!product.isAvailable(quantity)) {
                throw new Exception("Product " + product.getName() + " is out of stock.");
            }
        }

        // Calculate shipping fees if there are shippable items
        if (!shippableItems.isEmpty()) {
            shippingFees = 30; // Flat shipping fee
            ShippingService.shipItems(shippableItems);
        }

        double amountToPay = subtotal + shippingFees;

        // Check if customer has sufficient balance
        if (customer.getBalance() < amountToPay) {
            throw new Exception("Insufficient balance.");
        }

        // Deduct amount from customer balance
        customer.updateBalance(amountToPay);

        // Reduce product quantities
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            product.reduceQuantity(entry.getValue());
        }

        // Print checkout receipt immediately
        System.out.println("\n** Checkout Receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            double totalPrice = entry.getKey().getPrice() * entry.getValue();
            System.out.println(entry.getValue() + "x " + entry.getKey().getName() + "\t" + (int) totalPrice);
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t" + (int) subtotal);
        System.out.println("Shipping\t" + (int) shippingFees);
        System.out.println("Amount\t\t" + (int) amountToPay);
        System.out.println("Customer Balance\t" + (int) customer.getBalance());
    }
}
