package doAn;
import java.time.LocalDate;

public class FoodProduct extends Product {
    private LocalDate expiryDate;

    public FoodProduct(String id, String name, double price, int quantity, LocalDate expiryDate) {
        super(id, name, price, quantity);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String getDescription() {
        return "Loai: Thuc pham, Han su dung: " + expiryDate.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ", Han su dung: " + expiryDate.toString();
    }
}
