import java.util.Date;

public class PerishableProduct extends AbstractProduct implements Perishable {
    protected Date expirationDate;

    public PerishableProduct(int id, String name, double price, int quantity, Date expirationDate) {
        super(id, name, price, quantity);
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        Date today = new Date();
        return today.after(expirationDate);
    }
}
