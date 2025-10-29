package doAn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dsSanpham implements IManager {

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

		Product product = new Product(id, name, price, quantity);
		DataStore.productList.add(product);
	}

	@Override

	public void sua(String id) {
		for (int i = 0; i < DataStore.productList.size(); i++) {
			if (DataStore.productList.get(i).getId().equals(id)) {
				System.out.println("nhap ten san pham moi: ");
				String newname = scanner.nextLine();

				System.out.print("nhap id san pham moi : ");
				String newid;
				while (true) {
					newid = scanner.nextLine();
					boolean exists = false;
					for (Product p : DataStore.productList) {
						if (p.getId().equals(newid) && !p.getId().equals(id)) {
							exists = true;
							break;
						}
					}
					if (!exists)
						break;
					System.out.println("ID san pham da ton tai, vui long nhap lai!");
				}

				System.out.print("cap nhat gia san pham moi: ");
				double price = scanner.nextDouble();

				System.out.print("cap nhat so luong san pham: ");
				int quantity = scanner.nextInt();
				scanner.nextLine();

				Product updateproduct = new Product(newid, newname, price, quantity);
				DataStore.productList.set(i, updateproduct);
				return;
			}
		}
		System.out.println("Khong tim thay id san pham trong ds. ");
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
				String id = part[0];
				String name = part[1];
				double price = Double.parseDouble(part[2]);
				int quantity = Integer.parseInt(part[3]);
				DataStore.productList.add(new Product(id, name, price, quantity));
			}

		} catch (IOException e) {
			System.out.println("Error reading employee file: " + e.getMessage());
		}

	}

}
