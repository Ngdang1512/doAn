package doAn;
import java.io.IOException;
import java.util.Scanner;

public class Maincontrol {

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in) 
        ) {
            int choice = -1;
            do {
                System.out.println("\n--- CHUONG TRING QUAN LY SIEU THI MINI ---");
                System.out.println("1. Quan ly khach hang");
                System.out.println("2. Quan ly nhan vien");
                System.out.println("3. Quan ly san pham");
                System.out.println("4. Quan ly hoa don");
                System.out.println("0. Thoat chuong trinh");
                System.out.print("Nhap lua chon cua ban: ");
                
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    
                    // Xử lý lựa chọn
                    switch (choice) {
                        case 1 -> {
                           
                            CustomerManager.runCustomerManager(args, scanner);
                        }
                        case 2 -> {
                           
                            EmployeeManager.runEmployeeManager(args, scanner);
                        }
                        case 3 -> {
                           
                            ProductManager.runProductManager(args, scanner);
                        }
                        case 4 -> {
                           
                            invoiceManager.runInvoiceManager(args, scanner);
                        }
                        case 0 -> System.out.println("da thoat chuong trinh.");
                        default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
                    }
                } else {
                    System.out.println("Loi: Vui long nhap mot so hop le.");
                    scanner.nextLine();
                }
            } while (choice != 0);
        }   
    }
}
