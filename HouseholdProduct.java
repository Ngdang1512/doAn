package doAn;

public class HouseholdProduct extends Product {
    private String category;

    public HouseholdProduct(String id, String name, double price, int quantity, String category) {
        super(id, name, price, quantity);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getDescription() {
        return "Loai: Do gia dung, Danh muc: " + category;
    }

    @Override
    public String toString() {
        return super.toString() + ", Danh muc: " + category;
    }
}
