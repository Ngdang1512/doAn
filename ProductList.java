package doAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class ProductList implements IManager {

	Scanner scanner = new Scanner(System.in);

	@Override
	public void them() {
		// Kiểm tra tên sản phẩm không rỗng
		String name;
		while (true) {
			System.out.print("nhap ten san pham can them: ");
			name = scanner.nextLine();
			if (!name.trim().isEmpty())
				break;
			System.out.println("Ten san pham khong duoc de trong!");
		}

		String id;
		while (true) {
			System.out.print("nhap id san pham can them: ");
			id = scanner.nextLine();
			boolean exists = false;
			for (Product p : DataStore.productList) {
				if (p.getId().equals(id)) {
					exists = true;
					break;
				}
			}
			if (!exists)
				break;
			System.out.println("ID san pham da ton tai, vui long nhap lai!");
		}

		// Kiểm tra giá
		double price;
		while (true) {
			System.out.print("nhap gia thanh san pham can them: ");
			price = scanner.nextDouble();
			if (price >= 0)
				break;
			System.out.println("Gia san pham phai >= 0!");
		}

		// Kiểm tra số lượng
		int quantity;
		while (true) {
			System.out.print("nhap so luong san pham : ");
			quantity = scanner.nextInt();
			scanner.nextLine();
			if (quantity >= 0)
				break;
			System.out.println("Số lượng phải >= 0!");
		}

		System.out.println("Chon loai san pham:");
		System.out.println("1. Thuc pham");
		System.out.println("2. Do gia dung");
		System.out.print("Lua chon cua ban: ");
		int typeChoice = Integer.parseInt(scanner.nextLine());

		Product newProduct = null;
		if (typeChoice == 1) {
			System.out.print("Nhap ngay het han (vi du: 2025-12-31): ");
			String dateString = scanner.nextLine();
			LocalDate expiryDate = LocalDate.parse(dateString);
			
			newProduct = new FoodProduct(id, name, price, quantity, expiryDate);
			
		} else if (typeChoice == 2) {
			System.out.print("Nhap danh muc (Vd: Ve sinh): ");
			String category = scanner.nextLine();
			
			newProduct = new HouseholdProduct(id, name, price, quantity, category);
		}

		if (newProduct != null) {
			DataStore.productList.add(newProduct); // Thêm sản phẩm mới vào DataStore
			System.out.println("Da them san pham thanh cong!");
		} else {
			System.out.println("Lua chon loai khong hop le.");
		}
	}

	@Override
	public void sua(String id) {
		Product productToUpdate = null;

		for (int i = 0; i < DataStore.productList.size(); i++) {
			if (DataStore.productList.get(i).getId().equals(id)) {
				productToUpdate = DataStore.productList.get(i);
				break;
			}
		}

		if (productToUpdate != null) {
			System.out.println("--- Cap nhat thong tin cho san pham: " + productToUpdate.getName() + " ---");
			System.out.print("Nhap ten moi (bo qua neu khong doi): ");
			String newName = scanner.nextLine();
			if (!newName.trim().isEmpty()) {
				productToUpdate.setName(newName);
			}

			System.out.print("Nhap gia moi (nhap so am neu khong doi): ");
			double newPrice = Double.parseDouble(scanner.nextLine());
			if (newPrice >= 0) {
				productToUpdate.setPrice(newPrice);
			}

			System.out.print("Nhap so luong moi (nhap so am neu khong doi): ");
			int newQuantity = Integer.parseInt(scanner.nextLine());
			if (newQuantity >= 0) {
				productToUpdate.setQuantity(newQuantity);
			}

			if (productToUpdate instanceof FoodProduct) {
				System.out.print("Nhap HSD moi (yyyy-MM-dd, bo qua neu khong doi): ");
				String newDate = scanner.nextLine();
				if (!newDate.trim().isEmpty()) {
					((FoodProduct) productToUpdate).setExpiryDate(LocalDate.parse(newDate));
				}
			} else if (productToUpdate instanceof HouseholdProduct) {
				System.out.print("Nhap danh muc moi (bo qua neu khong doi): ");
				String newCategory = scanner.nextLine();
				if (!newCategory.trim().isEmpty()) {
					((HouseholdProduct) productToUpdate).setCategory(newCategory);
				}
			}
			
			System.out.println("Da cap nhat san pham thanh cong!");
			// Không cần .set() vì chúng ta đang sửa trực tiếp đối tượng
			return;
		}
		System.out.println("khong tim thay id san pham trong danh sach. ");
	}

	@Override
	public void xoa(String id) {
		for (int i = 0; i < DataStore.productList.size(); i++) {
			if (DataStore.productList.get(i).getId().equals(id)) {
				DataStore.productList.remove(i);
				Employee.decreaseTotalEmployees();
				System.out.println("da xoa thanh cong. ");

				return;

			}
		}
		System.out.println("khong tim thay id san pham trong danh sach. ");
	}

	@Override
	public void timkiem(String id) {
		for (Product product : DataStore.productList) {
			if (product.getId().equals(id)) {
				System.out.println(product.toString());
				return;
			}
		}
		System.out.println("khong tim thay san pham trong danh sach. ");

	}

	@Override
	public void xuat() {
		if (DataStore.productList.isEmpty()) {
			System.out.println("danh sach trong ");
		} else {
			for (Product product : DataStore.productList) {
				System.out.println(product.toString());
			}
		}
	}

	public void readfile(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] part = line.split(",");
				if (part.length < 6) continue;

				String type = part[0];
				String id = part[1];
				String name = part[2];
				double price = Double.parseDouble(part[3]);
				int quantity = Integer.parseInt(part[4]);
				String specificDetail = part[5];

				Product newProduct = null;
				if (type.equals("FOOD")) {
					LocalDate expiryDate = LocalDate.parse(specificDetail);
					newProduct = new FoodProduct(id, name, price, quantity, expiryDate);
				} else if (type.equals("HOUSEHOLD")) {
					newProduct = new HouseholdProduct(id, name, price, quantity, specificDetail);
				}

				if (newProduct != null) {
					DataStore.productList.add(newProduct);
				}
			}
			System.out.println("Da doc file san pham thanh cong.");
		} catch (IOException e) {
			System.out.println("Loi khi doc file san pham: " + e.getMessage());
		}
	}

	public void writefile(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (Product p : DataStore.productList) {
				String line = "";
				if (p instanceof FoodProduct) {
					FoodProduct fp = (FoodProduct) p;
					line = "FOOD," + fp.getId() + "," + fp.getName() + "," + fp.getPrice() + "," + fp.getQuantity() + "," + fp.getExpiryDate().toString();
				} else if (p instanceof HouseholdProduct) {
					HouseholdProduct hp = (HouseholdProduct) p;
					line = "HOUSEHOLD," + hp.getId() + "," + hp.getName() + "," + hp.getPrice() + "," + hp.getQuantity() + "," + hp.getCategory();
				}
				
				if (!line.isEmpty()) {
					bw.write(line);
					bw.newLine();
				}
			}
			System.out.println("Da ghi du lieu san pham vao file: " + filename);
		} catch (IOException e) {
			System.out.println("Loi khi ghi file san pham: " + e.getMessage());
		}
	}
}
