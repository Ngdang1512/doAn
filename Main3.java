package doAn;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args, Scanner scanner) {
        // Khởi tạo danh sách khách hàng, sản phẩm, và nhân viên
        dsKH customerManager = new dsKH();
        dsSanpham productManager = new dsSanpham();
        dsEmployee employeeManager = new dsEmployee();

        // Khởi tạo danh sách hóa đơn
        dsHoadon invoiceManager = new dsHoadon(customerManager, productManager, employeeManager);

       
            int choice;
            do {
                System.out.println("\n--- INVOICE MANAGER ---");
                System.out.println("1. Them hoa don");
                System.out.println("2. Sua hoa don");
                System.out.println("3. Xoa hoa don");
                System.out.println("4. Tim kiem hoa don");
                System.out.println("5. Hien thi tat ca hoa don");
                System.out.println("6. Doc file");
                System.out.println("0. Thoat chuong trinh");
                System.out.print("Nhap lua chon cua ban: ");

                choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1 -> invoiceManager.them();  // Thêm hóa đơn mới
                        
                    case 2 -> {
                        System.out.print("Nhap ma hoa don can sua: ");
                        String updateId = scanner.nextLine();
                        invoiceManager.sua(updateId);  // Sửa hóa đơn theo ID
                    }
                        
                    case 3 -> {
                        System.out.print("Nhap ma hoa don can xoa: ");
                        String deleteId = scanner.nextLine();
                        invoiceManager.xoa(deleteId);  // Xóa hóa đơn theo ID
                    }
                        
                    case 4 -> {
                        System.out.print("Nhap ma hoa don can tim: ");
                        String searchId = scanner.nextLine();
                        invoiceManager.timkiem(searchId);  // Tìm kiếm hóa đơn theo ID
                    }
                    case 5 -> invoiceManager.xuat();  // Hiển thị tất cả hóa đơn

                    case 6->{
                        invoiceManager.readfile("doAn/hoadon.txt");
                    }
                    case 0 -> System.out.println("Thoat chuong trinh.");
                        
                    default -> System.out.println("Lua chon khong hop le! Vui long nhap mot tuy chon hop le.");
                }
            } while (choice != 0);
        }
    }

