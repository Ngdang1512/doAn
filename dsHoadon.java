package doAn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class dsHoadon implements IManager {
    ArrayList<Hoadon> hoadonlist;
    dsKH customerManager; // Liên kết với danh sách khách hàng
    dsSanpham productManager; // Liên kết với danh sách sản phẩm
    dsEmployee employeeManager;
    Scanner scanner = new Scanner(System.in);

    public dsHoadon(dsKH customerManager, dsSanpham productManager, dsEmployee employeeManager) {
        this.hoadonlist = new ArrayList<>();
        this.customerManager = customerManager;
        this.productManager = productManager;
        this.employeeManager = employeeManager;
    }

    @Override
    public void them() {
        System.out.println("--- Them Hoa Don ---");
        String id;
        while (true) {
            System.out.print("Nhap ma hoa don: ");
            id = scanner.nextLine();
            boolean exists = false;
            for (Hoadon hd : hoadonlist) {
                if (hd.getid().equals(id)) {
                    exists = true;
                    break;
                }
            }
            if (!exists)
                break;
            System.out.println("Ma hoa don da ton tai, vui long nhap lai!");
        }

        // Chọn khách hàng từ danh sách
        System.out.println("--- Danh Sach Khach Hang ---");
        customerManager.xuat();
        System.out.print("Nhap ID khach hang tu danh sach: ");
        String customerId = scanner.nextLine();
        Customer customer = null;

        for (Customer c : DataStore.customerList) {
            if (c.getId().equals(customerId)) {
                customer = c;
                break; // Dừng vòng lặp ngay khi tìm thấy khách hàng
            }
        }
        if (customer == null) {
            System.out.println(" --- Khach hang chua co trong danh sach ---");

            System.out.print("nhap ten khach hang: ");
            String newname = scanner.nextLine();

            System.out.print("nhap sdt khach hang: ");
            String newsdt = scanner.nextLine();

            System.out.print("nhap level khach hang: ");
            String newlevel = scanner.nextLine();
            customer = new Customer(customerId, newname, newsdt, newlevel);

        }

        System.out.println("--- Danh Sach nhan vien ---");
        employeeManager.xuat();
        System.out.print("Nhap ID nhan vien tu danh sach: ");
        String employeeId = scanner.nextLine();
        Employee employee = null;

        for (Employee e : DataStore.employeeList) {
            if (e.getId().equals(employeeId)) {
                employee = e;
                break;
            }
        }
        if (employee == null) {
            System.out.println(" --- Nhan vien chua co trong danh sach ---");

            System.out.print("nhap ten nhan vien: ");
            String newname = scanner.nextLine();

            System.out.print("nhap sdt nhan vien: ");
            String newsdt = scanner.nextLine();

            System.out.print("nhap chuc vu nhan vien: ");
            String newchucvu = scanner.nextLine();

            System.out.print("nhap muc luong nhan vien: ");
            double newluong = scanner.nextDouble();
            scanner.nextLine();

            employee = new Employee(employeeId, newname, newsdt, newchucvu, newluong);
            DataStore.employeeList.add(employee);
        }

        // Nhập thời gian hóa đơn
        System.out.print("Nhap thoi gian lap hoa don (dd/MM/yyyy HH:mm:ss) hoac bo qua de lay thoi gian hien tai: ");
        String thoigian = scanner.nextLine();
        if (thoigian.trim().isEmpty()) {
            thoigian = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }
        Hoadon newhoadon = new Hoadon(id, customer, employee, thoigian);

        // Chọn sản phẩm từ danh sách
        System.out.println("--- Danh Sach San Pham ---");
        productManager.xuat();
        String choice = null;
        do {
            System.out.print("Nhap ma san pham: ");
            String productId = scanner.nextLine();

            Product product = null;
            for (Product p : DataStore.productList) {
                if (p.getId().equals(productId)) {
                    product = p;
                    break;
                }
            }

            if (product == null) {
                System.out.println("Khong tim thay san pham.");
                continue; // Quay lại vòng lặp để nhập sản phẩm khác
            }

            int quantity;
            while (true) {
                System.out.print("Nhap so luong: ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                if (quantity > 0 && quantity <= product.getQuantity()) {
                    break;
                }
                System.out.println("So luong khong hop le hoac vuot qua ton kho (" + product.getQuantity() + "), vui long nhap lai!");
            }

            newhoadon.addProduct(product, quantity);
            product.setQuantity(product.getQuantity() - quantity);

            System.out.print("Them san pham khac? (co/khong): ");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("co"));

        hoadonlist.add(newhoadon);
        System.out.println("Hoa don da duoc them thanh cong.");
    }

    @Override
    public void sua(String id) {
        for (int i = 0; i < hoadonlist.size(); i++) {
            if (hoadonlist.get(i).getid().equals(id)) {

                System.out.println("nhap id moi : ");
                String newid;
                while (true) {
                    newid = scanner.nextLine();
                    boolean exists = false;
                    for (Hoadon hd : hoadonlist) {
                        if (hd.getid().equals(newid) && !hd.getid().equals(id)) {
                            exists = true;
                            break;
                        }
                    }
                    if (!exists)
                        break;
                    System.out.println("Mã hóa đơn đã tồn tại, vui lòng nhập lại!");
                }

                System.out.println("--- Danh Sach Khach Hang ---");
                customerManager.xuat();
                System.out.print("Nhap ID khach hang tu danh sach: ");
                String customerId = scanner.nextLine();
                Customer customer = null;

                for (Customer c : DataStore.customerList) {
                    if (c.getId().equals(customerId)) {
                        customer = c;
                        break; // Dừng vòng lặp ngay khi tìm thấy khách hàng
                    }
                }
                if (customer == null) {
                    System.out.println(" --- Khach hang chua co trong danh sach ---");

                    System.out.print("nhap ten khach hang: ");
                    String newname = scanner.nextLine();

                    System.out.print("nhap sdt khach hang: ");
                    String newsdt = scanner.nextLine();

                    System.out.print("nhap level khach hang: ");
                    String newlevel = scanner.nextLine();
                    customer = new Customer(customerId, newname, newsdt, newlevel);

                }

                System.out.println("--- Danh Sach nhan vien ---");
                customerManager.xuat();

                Employee employee = null;
                do {

                    System.out.print("Nhập ID nhan vien tu danh sach: ");
                    String employeeId = scanner.nextLine();

                    for (Employee e : DataStore.employeeList) {
                        if (e.getId().equals(employeeId)) {
                            employee = e;
                            break; // Dừng vòng lặp ngay khi tìm thấy nhan vien
                        }
                    }
                    if (employee == null) {
                        System.out.println(" --- ma nhan vien chua co trong danh sach ---");
                    }

                } while (employee == null);

                Hoadon newhoadon = new Hoadon(newid, customer, employee);

                // Chọn sản phẩm từ danh sách
                System.out.println("--- Danh Sach San Pham ---");
                productManager.xuat();

                String choice = null;
                do {
                    System.out.print("Nhap ma san pham: ");
                    String productId = scanner.nextLine();

                    Product product = null;
                    for (Product p : DataStore.productList) {
                        if (p.getId().equals(productId)) {
                            product = p;
                            break;
                        }
                    }

                    if (product == null) {
                        System.out.println("Khong tim thay san pham.");
                        continue; // Quay lại vòng lặp để nhập sản phẩm khác
                    }

                    int quantity;
                    while (true) {
                        System.out.print("Nhap so luong: ");
                        quantity = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer
                        if (quantity > 0 && quantity <= product.getQuantity()) {
                            break;
                        }
                        System.out.println("So luong khong hop le hoac vuot qua ton kho (" + product.getQuantity() + "), vui long nhap lai!");
                    }

                    newhoadon.addProduct(product, quantity);
                    product.setQuantity(product.getQuantity() - quantity);

                    System.out.print("Them san pham khac? (co/khong): ");
                    choice = scanner.nextLine();
                } while (choice.equalsIgnoreCase("co"));

                hoadonlist.set(i, newhoadon);
                System.out.println("Hoa don da duoc sua thanh cong.");
            }
        }
    }

    @Override
    public void xoa(String id) {
        for (int i = 0; i < hoadonlist.size(); i++) {
            if (hoadonlist.get(i).getid().equals(id)) {
                hoadonlist.remove(i);
                System.out.println("da xoa thanh cong.");
                return;
            }
        }
        System.out.println("khong tim thay id hoa don");
    }

    @Override
    public void timkiem(String id) {
        for (Hoadon hoadon : hoadonlist) {
            if (hoadon.getid().equals(id)) {
                System.out.println(hoadon.toString());
                return;

            }
        }
        System.out.println("khong tim thay id hoa don");

    }

    @Override
    public void xuat() {
        if (hoadonlist.isEmpty()) {
            System.out.println("Danh sach hoa don trong.");
        } else {
            for (Hoadon invoice : hoadonlist) {
                System.out.println(invoice.toString());
            }
        }
    }

    public void readfile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(", ");
                String invoiceId = data[0];
                String customerId = data[1];
                String employeeId = data[2];
                String[] productData = data[3].split(";");

                Customer customer = null;
                for (Customer c : DataStore.customerList) {
                    if (c.getId().equals(customerId)) {
                        customer = c;
                        break;
                    }
                }
                if (customer == null) {
                    System.out.println("Bo qua hoa don nay.");
                    continue;
                }
                Employee employee = null;
                for (Employee e : DataStore.employeeList) {
                    if (e.getId().equals(employeeId)) {
                        employee = e;
                        break;
                    }
                }

                if (employee == null) {
                    System.out.println(" Bo qua hoa don nay.");
                    continue;
                }
                Hoadon hoadon = new Hoadon(invoiceId, customer, employee);
                for (String pd : productData) {
                    String[] productParts = pd.split("-");
                    String productId = productParts[0];
                    int quantity = Integer.parseInt(productParts[1]);

                    Product product = null;
                    for (Product p : DataStore.productList) {
                        if (p.getId().equals(productId)) {
                            product = p;
                            break;
                        }
                    }

                    if (product == null) {
                        System.out.println(" Bo qua san pham nay.");
                        continue;
                    }

                    hoadon.addProduct(product, quantity);
                }

                hoadonlist.add(hoadon);
            }
            System.out.println("Doc du lieu hoa don tu file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }

}
