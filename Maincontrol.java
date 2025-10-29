package doAn;
import java.io.IOException;
import java.util.Scanner;

public class Maincontrol {

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in) 
        ) {
            int choice = -1;
            do {
                // Hiển thị menu
                System.out.println("\nChon chuong trinh de chay:");
                System.out.println("1. Quan ly khach hang");
                System.out.println("2. Quan ly nhan vien");
                System.out.println("3. Quan ly san pham");
                System.out.println("4. Quan ly hoa don");
                System.out.println("0. Thoat chuong trinh");
                System.out.print("Nhap lua chon cua ban: ");
                
                // Kiểm tra đầu vào
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Xóa bộ đệm sau khi nhập số
                    
                    // Xử lý lựa chọn
                    switch (choice) {
                        case 1 -> {
                           
                            Main.main(args, scanner); // Truyền Scanner cho lớp Main
                        }
                        case 2 -> {
                           
                            Main1.main(args, scanner); // Truyền Scanner cho lớp Main1
                        }
                        case 3 -> {
                           
                            Main2.main(args, scanner); // Truyền Scanner cho lớp Main2
                        }
                        case 4 -> {
                           
                            Main3.main(args, scanner); // Truyền Scanner cho lớp Main2
                        }
                        case 0 -> System.out.println("da thoat chuong trinh.");
                        default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
                    }
                } else {
                    // Nếu không phải số, thông báo lỗi và bỏ qua đầu vào không hợp lệ
                    System.out.println("Loi: Vui long nhap mot so hop le.");
                    scanner.nextLine(); // Loại bỏ đầu vào không hợp lệ
                }
            } while (choice != 0); // Thoát khi người dùng nhập 0
        }   
    }
}
