package doAn;

import java.util.HashSet;
import java.util.Set;

// Lớp con Customer kế thừa từ Person
public class Customer extends Person {
    private String membershipLevel;

    // Set lưu trữ tất cả ID đã dùng
    private static final Set<String> existingIds = new HashSet<>();
    private static final Set<String> existingPhones = new HashSet<>();

    private static int totalcustomers = 0; // Đếm số lượng nhân viên

    public Customer(String id, String name, String sdt, String membershipLevel) {
        super(id, name, sdt);

        if (existingIds.contains(id)) {
            throw new IllegalArgumentException("ID da ton tai: " + id);
        }
        if (existingPhones.contains(sdt)) {
            throw new IllegalArgumentException("So dien thoai da ton tai: " + sdt);
        }

        existingIds.add(id);
        existingPhones.add(sdt);

        this.membershipLevel = membershipLevel;
        totalcustomers++;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    // Ghi đè toString() để trả về thông tin chi tiết của Customer
    @Override
    public String toString() {
        return super.toString() + ", so hang khach hang: " + membershipLevel;
    }

    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    public static void decreaseTotalCustomer() {
        if (totalcustomers > 0) {
            totalcustomers--;
        }
    }

    public static int gettotalcustomer() {
        return totalcustomers;
    }

    public static boolean isIdExists(String id) {
        return existingIds.contains(id);
    }

    public static boolean isPhoneExists(String phone) {
        return existingPhones.contains(phone);
    }
}
