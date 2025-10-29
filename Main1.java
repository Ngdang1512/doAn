package doAn;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args, Scanner scanner) {
        dsEmployee employeeManager = new dsEmployee(){}; // Tạo đối tượng quản lý nhân viên
        
            int choice;
            
            do {
                System.out.println("\n--- EMPLOYEE MANAGEMENT ---");
                System.out.println("1. Them nhan vien");
                System.out.println("2. sua du lieu nhan vien");
                System.out.println("3. xoa nhan vien");
                System.out.println("4. tim kiem nhan vien bang id");
                System.out.println("5. Xem danh sach nhan vien");
                System.out.println("6.doc file");
                System.out.println("0. Thoat");
                System.out.print("Nhap lua chon cua ban: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng trống sau khi nhập số
                
                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- Them nhan vien ---");
                        employeeManager.them();
                    }
                        
                    case 2 -> {
                        System.out.println("\n--- Cap nhat nhan vien ---");
                        System.out.print("nhap id nhan vien can chinh sua: ");
                        String updateId = scanner.nextLine();
                        employeeManager.sua(updateId);
                    }
                        
                    case 3 -> {
                        System.out.println("\n--- Xoa nhan vien ---");
                        System.out.print("Nhap id nhan vien can xoa: ");
                        String deleteId = scanner.nextLine();
                        employeeManager.xoa(deleteId);
                    }
                        
                    case 4 -> {
                        System.out.println("\n--- tim kiem nhan vien bang id ---");
                        System.out.print("nhap id nhan vien can tim: ");
                        String findId = scanner.nextLine();
                        employeeManager.timkiem(findId);
                    }
                        
                    case 5 -> {
                        System.out.println("\n--- Danh sach nhan vien ---");
                        employeeManager.xuat();
                    }
                    case 6 ->{
                        employeeManager.readfile("doAn/nhanvien.txt");
                    }
                    case 0 -> System.out.println("Da thoat chuong trinh...");
                        
                    default -> System.out.println("Lua chon khong hop le! Vui long nhap mot tuy chon hop le.");
                }
            } while (choice != 0);
    }
}

