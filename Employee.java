package doAn;

public class Employee extends Person {
    private static int totalEmployees = 0; // Đếm số lượng nhân viên

    private String position;
    private double salary;

    public Employee(String id, String name, String phone, String position, double salary) {
        super(id, name, phone);
        this.position = position;
        this.salary = salary;
        totalEmployees++; // Tăng số lượng nhân viên khi tạo mới
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() + ",  chuc vu: " + getPosition() + ",  muc luong: " + getSalary();
    }

    public static void decreaseTotalEmployees() {
        if (totalEmployees > 0) {
            totalEmployees--;
        }
    }

    // Phương thức static để lấy tổng số lượng nhân viên
    public static int getTotalEmployees() {
        return totalEmployees;
    }
}