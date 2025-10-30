package doAn;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        dsKH customerManager = new dsKH();

        int choice;

        try {
            do {
                System.out.println("\n--- CUSTOMER MANAGEMENT ---");
                System.out.println("1. Them khach hang");
                System.out.println("2. sua du lieu khach hang");
                System.out.println("3. xoa khach hang");
                System.out.println("4. tim kiem khach hang bang id");
                System.out.println("5. Xem danh sach khach hang");
                System.out.println("6. Doc file khach hang.");
                System.out.println("7. Ghi file khach hang.");
                System.out.println("0. Thoat");
                System.out.print("Nhap lua chon cua ban: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Đọc dòng trống sau khi nhập số

                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- Them khach hang ---");
                        customerManager.them();
                    }

                    case 2 -> {
                        System.out.println("\n--- Cap nhat khach hang ---");
                        System.out.print("nhap id khach hang can chinh sua: ");
                        String updateId = scanner.nextLine();
                        customerManager.sua(updateId);
                    }

                    case 3 -> {
                        System.out.println("\n--- Xoa khach hang ---");
                        System.out.print("Nhap id khach hang can xoa: ");
                        String deleteId = scanner.nextLine();
                        customerManager.xoa(deleteId);
                    }

                    case 4 -> {
                        System.out.println("\n--- tim kiem khach hang bang id ---");
                        System.out.print("nhap id khach can tim: ");
                        String findId = scanner.nextLine();
                        customerManager.timkiem(findId);
                    }

                    case 5 -> {
                        System.out.println("\n--- Danh sach khach hang ---");
                        customerManager.xuat();
                    }

                    case 6 -> {
                        customerManager.readfile("doAn/khachhang.txt");
                    }

                    case 7 -> {
                        customerManager.writefile("writeKH.txt");
                    }

                    case 0 -> System.out.println("Da thoat chuong trinh...");

                    default -> System.out.println("Lua chon khong hop le! Vui long nhap mot tuy chon hop le.");
                }
            } while (choice != 0);
        } finally {
            scanner.close();
        }
    }
}




