package doAn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Invoice {
    private String id;
    private Customer customer;
    private Employee employee;
    private ArrayList<InvoiceDetails> detailsList;
    private double totalPrice;
    private String thoigian;

    public Invoice(String id, Customer customer, Employee employee, String thoigian) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.detailsList = new ArrayList<>();
        this.totalPrice = 0.0;
        this.thoigian = thoigian;
    }

    public Invoice(String id, Customer customer, Employee employee) {
        this(id, customer, employee, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            InvoiceDetails detail = new InvoiceDetails(product, quantity, product.getPrice());
            this.detailsList.add(detail);
            this.totalPrice += detail.getSubtotal();
            product.setQuantity(product.getQuantity() - quantity);
        } else {
            System.out.println("Khong du so luong san pham: " + product.getName());
        }
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getid() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double Price) {
        this.totalPrice = Price;
    }

    public ArrayList<String> getProductIds() {
        ArrayList<String> productIds = new ArrayList<>();
        for (InvoiceDetails detail : this.detailsList) {
            productIds.add(detail.getProduct().getId());
        }
        return productIds;
    }

    public boolean containsProduct(String productId) {
        for (String id : getProductIds()) {
            if (id.equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("Ma hoa don: ").append(id).append("\n");
        sb.append("Thoi gian: ").append(thoigian).append("\n");
        sb.append("Khach hang: ").append(customer.getName()).append(" (ID: ").append(customer.getId()).append(")\n");
        sb.append("Nhan vien: ").append(employee.getName()).append(" (ID: ").append(employee.getId()).append(")\n");
        sb.append("--------------- San Pham ---------------\n");
        sb.append(String.format("%-10s %-20s %-10s %-10s %-10s\n",
        "ID", "Ten", "Gia", "So luong", "Thanh tien"));
        for (InvoiceDetails detail : this.detailsList) {
            sb.append(detail.toString()).append("\n");
        }
        sb.append("----------------------------------------\n");
        sb.append(String.format("Tong tien: %.2f\n", totalPrice));
        sb.append("========================================\n");
        return sb.toString();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public ArrayList<InvoiceDetails> getDetailsList() {
        return detailsList;
    }
}
