// Main.java
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Product> products = new HashMap<>();
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static Map<Integer, Order> orders = new HashMap<>();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        prepopulateData(); // Pre-populate data
        boolean running = true;
        while (running) {
            menu();
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }
            try {
                switch (choice) {
                    case 1:
                        viewAllProducts();
                        break;
                    case 2:
                        viewAllCustomers();
                        break;
                    case 3:
                        addNewProduct();
                        break;
                    case 4:
                        addNewCustomer(); // New method
                        break;
                    case 5:
                        updateProductQuantity();
                        break;
                    case 6:
                        addProductsToCart();
                        break;
                    case 7:
                        viewCart();
                        break;
                    case 8:
                        proceedToCheckout();
                        break;
                    case 9:
                        viewAllOrders();
                        break;
                    case 10:
                        running = false;
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again...");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // customer and product in testCases
    private static void prepopulateData() {
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            now = sdf.parse("10-12-2024")   ;
            Product cheese = new PerishableShippableProduct(1, "Cheese", 100, 10, now, 0.2);
            Product biscuits = new PerishableShippableProduct(2, "Biscuits", 150, 10, now, 0.7);
            Product tv = new NonPerishableShippableProduct(3, "TV", 2000, 5, 20.0);
            Product scratchCard = new NonPerishableProduct(4, "Scratch Card", 50, 100);

            products.put(cheese.getId(), cheese);
            products.put(biscuits.getId(), biscuits);
            products.put(tv.getId(), tv);
            products.put(scratchCard.getId(), scratchCard);

            Customer customer = new Customer(1, "John Doe", "johndoe@example.com", "123 Main St", 5000);
            customers.put(customer.getId(), customer);
            viewAllProducts();
            viewAllCustomers();
            System.out.println("Pre-populated products and customers.");
        } catch (Exception e) {
            System.out.println("Error pre-populating data: " + e.getMessage());
        }
    }

    private static void menu() {
        System.out.println("\n===== E-Commerce System Menu =====");
        System.out.println("1- View all products");
        System.out.println("2- View all customers");
        System.out.println("3- Add a new product");
        System.out.println("4- Add a new customer"); // New option
        System.out.println("5- Update product quantity");
        System.out.println("6- Add products to cart");
        System.out.println("7- View cart");
        System.out.println("8- Proceed to checkout");
        System.out.println("9- View all orders");
        System.out.println("10- Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewAllProducts() {
        if (products.isEmpty()) {
            System.out.println("\nNo products available.");
            return;
        }
        System.out.println("\n--- Products ---");
        for (Product product : products.values()) {
            System.out.println("ID: " + product.getId() +
                    ", Name: " + product.getName() +
                    ", Price: " + product.getPrice() +
                    ", Stock: " + product.getQuantity());
        }
    }

    private static void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("\nNo customers available.");
            return;
        }
        System.out.println("\n--- Customers ---");
        for (Customer customer : customers.values()) {
            System.out.println("ID: " + customer.getId() +
                    ", Name: " + customer.getName() +
                    ", Email: " + customer.getEmail() +
                    ", Address: " + customer.getAddress() +
                    ", Balance: " + customer.getBalance());
        }
    }

    private static void addNewProduct() {
        try {
            System.out.println("\n--- Add a New Product ---");
            System.out.print("Enter Product Id: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (products.containsKey(id)) {
                System.out.println("Product ID already exists.");
                return;
            }
            System.out.print("Enter product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter product price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter product stock quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            System.out.print("Is the product perishable? (yes/no): ");
            String isPerishableInput = scanner.nextLine().trim().toLowerCase();
            boolean isPerishable = isPerishableInput.equals("yes");

            Date expirationDate = null;
            if (isPerishable) {
                System.out.print("Enter expiration date (yyyy-MM-dd): ");
                String expirationDateStr = scanner.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                expirationDate = sdf.parse(expirationDateStr);
            }

            System.out.print("Is the product shippable? (yes/no): ");
            String isShippableInput = scanner.nextLine().trim().toLowerCase();
            boolean isShippable = isShippableInput.equals("yes");

            double weight = 0;
            if (isShippable) {
                System.out.print("Enter product weight in kg: ");
                weight = Double.parseDouble(scanner.nextLine());
            }

            Product product;
            if (isPerishable && isShippable) {
                product = new PerishableShippableProduct(id, name, price, quantity, expirationDate, weight);
            } else if (isPerishable) {
                product = new PerishableProduct(id, name, price, quantity, expirationDate);
                } else if (isShippable) {
                product = new NonPerishableShippableProduct(id, name, price, quantity, weight);
            } else {
                product = new NonPerishableProduct(id, name, price, quantity);
            }

            products.put(id, product);
            System.out.println("Product added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for ID, price, quantity, and weight.");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    // Add a new customer
    private static void addNewCustomer() {
        try {
            System.out.println("\n--- Add a New Customer ---");
            System.out.print("Enter Customer Id: ");
            int id = Integer.parseInt(scanner.nextLine());
            if (customers.containsKey(id)) {
                System.out.println("Customer ID already exists.");
                return;
            }
            System.out.print("Enter Customer name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Customer email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Customer address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Customer balance: ");
            double balance = Double.parseDouble(scanner.nextLine());
            if (balance < 0) {
                System.out.println("Balance cannot be negative.");
                return;
            }
            Customer customer = new Customer(id, name, email, address, balance);
            customers.put(id, customer);
            System.out.println("Customer added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid numbers for ID and balance.");
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    // Update product quantity
    private static void updateProductQuantity() {
        try {
            System.out.println("\n--- Update Product Quantity ---");
            System.out.print("Enter Product ID to update: ");
            int productId = Integer.parseInt(scanner.nextLine());
            Product product = products.get(productId);
            if (product == null) {
                System.out.println("Product not found.");
                return;
            }
            System.out.println("Current quantity: " + product.getQuantity());
            System.out.print("Enter new quantity: ");
            int newQuantity = Integer.parseInt(scanner.nextLine());
            if (newQuantity < 0) {
                System.out.println("Quantity cannot be negative.");
                return;
            }
            // Update the product's quantity
            if (product instanceof AbstractProduct) {
                ((AbstractProduct) product).setQuantity(newQuantity);
                System.out.println("Product quantity updated successfully.");
            } else {
                System.out.println("Unable to update quantity for this product type.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("Error updating product quantity: " + e.getMessage());
        }
    }

    // Add products to cart
    private static void addProductsToCart() {
        System.out.println("\n--- Add Products to Cart ---");
        System.out.print("Enter customer Id: ");
        int customerId;
        try {
            customerId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid customer ID.");
            return;
        }
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        while (true) {
            System.out.print("Enter Product Id to add to cart or -1 to finish: ");
            int productId;
            try {
                productId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid product ID.");
                continue;
            }
            if (productId == -1) {
                break;
            }
            Product product = products.get(productId);
            if (product == null) {
                System.out.println("Product not found.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity.");
                continue;
            }
            try {
                cart.addProduct(product, quantity);
                System.out.println("Product added to cart.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                // Optionally, ask if the user wants to add the available quantity
                if (e.getMessage().contains("are available")) {
                    System.out.print("Do you want to add the available quantity? (yes/no): ");
                    String response = scanner.nextLine().trim().toLowerCase();
                    if (response.equals("yes") || response.equals("y")) {
                        // Extract available quantity from the error message
                        int availableQty = extractAvailableQuantity(e.getMessage());
                        if (availableQty > 0) {
                            try {
                                cart.addProduct(product, availableQty);
                                System.out.println("Added " + availableQty + " units of " + product.getName() + " to cart.");
                            } catch (Exception ex) {
                                System.out.println("Error: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }
    private static int extractAvailableQuantity(String message) {
        try {
            String[] parts = message.split(" ");
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].equals("Only")) {
                    return Integer.parseInt(parts[i + 1]);
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty.");
            return;
        }
        System.out.println("\n--- Cart Contents ---");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.println("Product ID: " + entry.getKey().getId() +
                    ", Name: " + entry.getKey().getName() +
                    ", Quantity: " + entry.getValue());
        }
    }

    private static void proceedToCheckout() {
        System.out.println("\n--- Proceed to Checkout ---");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add products to cart first.");
            return;
        }

        System.out.print("Enter customer Id: ");
        int customerId;
        try {
            customerId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid customer ID.");
            return;
        }
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        try {
            Checkout.checkout(customer, cart);

            double shippingFees = cart.getShippableItems().isEmpty() ? 0 : 30;
            double totalAmount = cart.calculateSubtotal() + shippingFees;

            int orderId = orders.size() + 1;
            Order order = new Order(orderId, customer, new ArrayList<>(cart.getItems().keySet()), totalAmount);
            orders.put(orderId, order);
            System.out.println("Order added successfully.");

            // Clear the cart after successful checkout
            cart.clear();
        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
        }
    }

    // View all orders
    private static void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("\nNo orders available.");
            return;
        }
        System.out.println("\n--- Orders ---");
        for (Order order : orders.values()) {
            System.out.println("Order ID: " + order.getId() +
                    ", Customer: " + order.getCustomer().getName() +
                    ", Total Amount: " + order.getTotalAmount());
        }
    }
}
