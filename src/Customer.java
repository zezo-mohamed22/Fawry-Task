public class Customer {
    private int id;
    private String name;
    private String email;
    private String address;
    private double balance;

    public Customer(int id, String name, String email, String address, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.balance = balance;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getAddress() { return address; }

    public double getBalance() { return balance; }

    public void updateBalance(double amount) { this.balance -= amount; }
}
