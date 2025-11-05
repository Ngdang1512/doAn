package doAn;

import java.util.Scanner;

public class invoiceManager {
    public static void runInvoiceManager(String[] args, Scanner scanner) {
        CustomerList customerManager = new CustomerList();
        ProductList productManager = new ProductList();
        EmployeeList employeeManager = new EmployeeList();

        InvoiceList invoiceManager = new InvoiceList(customerManager, productManager, employeeManager);

       
            int choice;
            do {
                System.out.println("\n--- INVOICE MANAGER ---");
                System.out.println("1. Them hoa don");
                System.out.println("2. Sua hoa don");
                System.out.println("3. Xoa hoa don");
                System.out.println("4. Tim kiem hoa don");
                System.out.println("5. Hien thi tat ca hoa don");
                System.out.println("6. Doc file hoa don.");
                System.out.println("7. Ghi file hoa don.");
                System.out.println("0. Thoat chuong trinh");
                System.out.print("Nhap lua chon cua ban: ");

                choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1 -> invoiceManager.them();
                        
                    case 2 -> {
                        System.out.print("Nhap ma hoa don can sua: ");
                        String updateId = scanner.nextLine();
                        invoiceManager.sua(updateId);
                    }
                        
                    case 3 -> {
                        System.out.print("Nhap ma hoa don can xoa: ");
                        String deleteId = scanner.nextLine();
                        invoiceManager.xoa(deleteId);
                    }
                        
                    case 4 -> {
                        System.out.print("Nhap ma hoa don can tim: ");
                        String searchId = scanner.nextLine();
                        invoiceManager.timkiem(searchId);
                    }

                    case 5 -> invoiceManager.xuat();

                    case 6->{
                        invoiceManager.readfile("doAn/hoadon.txt");
                    }

                    case 7 ->{
                        customerManager.writefile("doAn/khachhang.txt");
                    }
                    
                    case 0 -> System.out.println("Thoat chuong trinh.");
                        
                    default -> System.out.println("Lua chon khong hop le! Vui long nhap mot tuy chon hop le.");
                }
            } while (choice != 0);
        }
    }

