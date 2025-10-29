package doAn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class dsKH implements IManager {

	Scanner scanner = new Scanner(System.in);

	@Override
	public void them() {
		System.out.println("nhap ten KH can them: ");
		String name = scanner.nextLine();

		String id;
		while (true) {
			System.out.println("nhap id KH can them: ");
			id = scanner.nextLine();
			if (!Customer.isIdExists(id)) {
				break;
			}
			System.out.println("ID da ton tai, vui long nhap lai!");
		}

		String sdt;
		while (true) {
			System.out.println("nhap sdt KH can them: ");
			sdt = scanner.nextLine();
			if (!sdt.matches("\\d{10}")) {
				System.out.println("So dien thoai phai la 10 so!");
				continue;
			}
			if (!DataStore.isPhoneExistsAll(sdt)) {
				break;
			}
			System.out.println("So dien thoai da ton tai, vui long nhap lai!");
		}

		System.out.println("nhap level KH can them: ");
		String level = scanner.nextLine();

		Customer customer = new Customer(id, name, sdt, level);
		DataStore.customerList.add(customer);
	}

	@Override

	public void sua(String id) {
		for (int i = 0; i < DataStore.customerList.size(); i++) {
			Customer customer = DataStore.customerList.get(i);
			if (customer.getId().equals(id)) {
				boolean done = false;
				while (!done) {
					System.out.println("\nChon muc can sua:");
					System.out.println("1. Ten khach hang");
					System.out.println("2. ID khach hang");
					System.out.println("3. So dien thoai");
					System.out.println("4. Level");
					System.out.println("0. Thoat");
					System.out.print("Nhap lua chon cua ban: ");
					int choice = Integer.parseInt(scanner.nextLine());
					switch (choice) {
					case 1:
						System.out.println("Nhap ten moi: ");
						customer.setName(scanner.nextLine());
						break;
					case 2:
						String newid;
						while (true) {
							System.out.println("Nhap ID moi: ");
							newid = scanner.nextLine();
							if (!Customer.isIdExists(newid) || newid.equals(customer.getId())) {
								break;
							}
							System.out.println("ID da ton tai, vui long nhap lai!");
						}
						customer.setId(newid);
						break;
					case 3:
						String newsdt;
						while (true) {
							System.out.println("Nhap so dien thoai moi: ");
							newsdt = scanner.nextLine();
							if (!Customer.isPhoneExists(newsdt) || newsdt.equals(customer.getPhoneNumber())) {
								break;
							}
							System.out.println("So dien thoa da ton tai, vui long nhap lai!");
						}
						customer.setPhoneNumber(newsdt);
						break;
					case 4:
						System.out.println("Nhap level moi: ");
						((Customer) customer).setMembershipLevel(scanner.nextLine());
						break;
					case 0:
						done = true;
						break;
					default:
						System.out.println("Lua chon khong hop le!");
					}
				}
				System.out.println("Da cap nhat thong tin khach hang.");
				return;
			}
		}
		System.out.println("Khong tim thay ID khach hang trong ds.");
	}

	@Override
	public void xoa(String id) {
		for (int i = 0; i < DataStore.customerList.size(); i++) {
			if (DataStore.customerList.get(i).getId().equals(id)) {
				DataStore.customerList.remove(i);
				System.out.println("da xoa thanh cong. ");
				Customer.decreaseTotalCustomer();
				return;

			}
		}
		System.out.println("khong tim thay id KH trong ds. ");
	}

	@Override
	public void timkiem(String id) {
		for (Customer customer : DataStore.customerList) {
			if (customer.getId().equals(id)) {
				System.out.println(customer.toString());
				return;
			}
		}
		System.out.println("khong tim thay KH trong ds. ");

	}

	@Override
	public void xuat() {
		if (DataStore.customerList.isEmpty()) {
			System.out.println("ds trong ");
		} else {
			for (Customer customer : DataStore.customerList) {
				System.out.println(customer.toString());
			}
			System.out.println("So luong khach hang trong ds: " + Customer.gettotalcustomer());
		}
	}

	public void readfile(String filename) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] part = line.split(",");
				if (part.length == 4) {
					String id = part[0];
					String name = part[1];
					String sdt = part[2];
					String level = part[3];

					Customer customer = new Customer(id, name, sdt, level);
					DataStore.customerList.add(customer);
				} else {
					System.out.println("Dong khong hop le: " + line);
				}

			}
			System.out.println("Da doc du lieu tu file " + filename);

		} catch (FileNotFoundException e) {
			System.out.println("Loi khi doc file: " + e.getMessage());
		}
	}

	public void writefile(String filename) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (Customer customer : DataStore.customerList) {
				bw.write(customer.getId() + " ," + customer.getName() + " ," + customer.getPhoneNumber() + " ,"
						+ ((Customer) customer).getMembershipLevel());
				bw.newLine();
			}
			System.out.println("Da ghi du lieu vao file. ");

		} catch (IOException e) {
			System.out.println("Loi khi ghi file: " + e.getMessage());
		}
	}
}
