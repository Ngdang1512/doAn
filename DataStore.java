package doAn;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    // Danh sach khach hang
    public static List<Customer> customerList = new ArrayList<>();

    // Danh sach nhan vien
    public static List<Employee> employeeList = new ArrayList<>();

    // Danh sach san pham
    public static List<Product> productList = new ArrayList<>();

    // Danh sach hoa don
    public static List<Hoadon> invoiceList = new ArrayList<>();

    public static boolean isPhoneExistsAll(String sdt) {
        for (Customer kh : customerList) {
            if (kh.getPhoneNumber().equals(sdt)) return true;
        }
        for (Employee nv : employeeList) {
            if (nv.getPhoneNumber().equals(sdt)) return true;
        }
        return false;
    }
}
