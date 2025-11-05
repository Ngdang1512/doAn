package doAn;

public class InvoiceDetails {
    private Product product;
    private int quantity;
    private double unitPrice;

    public InvoiceDetails(Product product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10.2f %-10d %-10.2f", 
            product.getId(), 
            product.getName(), 
            unitPrice, 
            quantity, 
            getSubtotal());
    }
}
