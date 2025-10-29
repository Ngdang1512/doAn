package doAn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Hoadon {
    private String id;
    private Customer customer;
    private Employee employee;
    private ArrayList<Product> productList;
    private double totalPrice;
    private String thoigian;

    public Hoadon(String id, Customer customer, Employee employee, String thoigian) {
        this.id = id;
        this.customer = customer;
        this.employee = employee;
        this.productList = new ArrayList<>();
        this.totalPrice = 0.0;
        this.thoigian = thoigian;
    }

    public Hoadon(String id, Customer customer, Employee employee) {
        this(id, customer, employee, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            productList.add(new Product(product.getId(), product.getName(), product.getPrice(), quantity));
            product.setQuantity(product.getQuantity() - quantity);
            totalPrice += product.getPrice() * quantity;
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
        for (Product product : productList) {
            productIds.add(product.getId());
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
        sb.append("Mã hóa đơn: ").append(id).append("\n");
        sb.append("Thời gian: ").append(thoigian).append("\n");
        sb.append("Khách hàng: ").append(customer.getName()).append(" (ID: ").append(customer.getId()).append(")\n");
        sb.append("Nhân viên: ").append(employee.getName()).append(" (ID: ").append(employee.getId()).append(")\n");
        sb.append("--------------- Sản phẩm ---------------\n");
        sb.append(String.format("%-10s %-20s %-10s %-10s\n", "ID", "Tên", "Giá", "Số lượng"));
        for (Product p : productList) {
            sb.append(String.format("%-10s %-20s %-10.2f %-10d\n", p.getId(), p.getName(), p.getPrice(), p.getQuantity()));
        }
        sb.append("----------------------------------------\n");
        sb.append(String.format("Tổng tiền: %.2f\n", totalPrice));
        sb.append("========================================\n");
        return sb.toString();
    }
}
