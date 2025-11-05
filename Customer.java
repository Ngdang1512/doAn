package doAn;

import java.util.HashSet;
import java.util.Set;

// Lớp con Customer kế thừa từ Person
public class Customer extends Person {
    private String membershipLevel;

    private static int totalcustomers = 0; // Đếm số lượng nhân viên

    public Customer(String id, String name, String sdt, String membershipLevel) {
        super(id, name, sdt);
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
        return super.toString() + ", Level: " + membershipLevel;
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
}
