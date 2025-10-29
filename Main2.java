package doAn;

import java.util.Scanner;

public class Main2 {
    
    public static void main(String[] args,Scanner scanner) {
        dsSanpham ProductManager = new dsSanpham();
       
            int choice;
                
                do {
                    System.out.println("\n--- PRODUCT MANAGEMENT ---");
                    System.out.println("1. Them san pham");
                    System.out.println("2. sua du lieu san pham");
                    System.out.println("3. xoa san pham");
                    System.out.println("4. tim kiem san pham bang id");
                    System.out.println("5. Xem danh sach san pham");
                    System.out.println("6. Doc file");
                    System.out.println("0. Thoat");
                    System.out.print("Nhap lua chon cua ban: ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Đọc dòng trống sau khi nhập số
                    
                    switch (choice) {
                        case 1 -> {
                            System.out.println("\n--- Them san pham ---");
                            ProductManager.them();
                        }
                            
                        case 2 -> {
                            System.out.println("\n--- Cap nhat san pham ---");
                            System.out.print("nhap id san pham can chinh sua: ");
                            String updateId = scanner.nextLine();
                            ProductManager.sua(updateId);
                        }
                            
                        case 3 -> {
                            System.out.println("\n--- Xoa san pham ---");
                            System.out.print("Nhap id san pham can xoa: ");
                            String deleteId = scanner.nextLine();
                            ProductManager.xoa(deleteId);
                        }
                            
                        case 4 -> {
                            System.out.println("\n--- tim kiem san pham bang id ---");
                            System.out.print("nhap id san pham can tim: ");
                            String findId = scanner.nextLine();
                            ProductManager.timkiem(findId);
                        }
                            
                        case 5 -> {
                            System.out.println("\n--- Danh sach san pham ---");
                            ProductManager.xuat();
                        }
                        case 6->{
                            ProductManager.readfile("doAn/sanpham.txt");
                        }
                        case 0 -> System.out.println("Da thoat chuong trinh...");
                            
                        default -> System.out.println("Lua chon khong hop le! Vui long nhap mot tuy chon hop le.");
                    }
                } while (choice != 0);
        }
    }





    

