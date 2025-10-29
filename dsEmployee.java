package doAn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class dsEmployee implements IManager {
	Scanner scanner = new Scanner(System.in);

	public dsEmployee() {
	}

	@Override
	public void them() {
		System.out.println("nhap ten NV can them: ");
		String name = scanner.nextLine();

		String id;
		while (true) {
			System.out.println("nhap id NV can them: ");
			id = scanner.nextLine();
			boolean exists = false;
			for (Employee emp : DataStore.employeeList) {
				if (emp.getId().equals(id)) {
					exists = true;
					break;
				}
			}
			if (!exists)
				break;
			System.out.println("ID nhân viên đã tồn tại, vui lòng nhập lại!");
		}

		String sdt;
		while (true) {
			System.out.println("nhap sdt NV can them: ");
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

		System.out.println("nhap chuc vu NV : ");
		String chucvu = scanner.nextLine();

		System.out.println("nhap muc luong NV : ");
		double luong = scanner.nextDouble();
		scanner.nextLine();

		Employee employee = new Employee(id, name, sdt, chucvu, luong);
		DataStore.employeeList.add(employee);
	}

	@Override
	public void sua(String id) {
		for (int i = 0; i < DataStore.employeeList.size(); i++) {
			if (DataStore.employeeList.get(i).getId().equals(id)) {
				System.out.println("nhap ten NV moi: ");
				String newname = scanner.nextLine();

				System.out.println("nhap id moi : ");
				String newid;
				while (true) {
					newid = scanner.nextLine();
					boolean exists = false;
					for (Employee emp : DataStore.employeeList) {
						if (emp.getId().equals(newid) && !emp.getId().equals(id)) {
							exists = true;
							break;
						}
					}
					if (!exists)
						break;
					System.out.println("ID nhan vien da ton tai. Vuii long nhap lai!");
				}

				System.out.println("nhap sdt moi: ");
				String newsdt = scanner.nextLine();

				System.out.println("nhap chuc vu moi: ");
				String chucvu = scanner.nextLine();

				System.out.println("nhap luong moi: ");
				double luong = scanner.nextDouble();
				scanner.nextLine();

				Employee updateemployee = new Employee(newid, newname, newsdt, chucvu, luong);
				Employee.decreaseTotalEmployees();
				DataStore.employeeList.set(i, updateemployee);
				return;
			}
		}
		System.out.println("Khong tim thay id nhan vien trong ds. ");
	}

	@Override
	public void xoa(String id) {
		for (int i = 0; i < DataStore.employeeList.size(); i++) {
			if (DataStore.employeeList.get(i).getId().equals(id)) {
				DataStore.employeeList.remove(i);
				Employee.decreaseTotalEmployees();
				System.out.println("da xoa thanh cong. ");
				return;
			}
		}
		System.out.println("khong tim thay id nhan vien trong ds. ");
	}

	@Override
	public void timkiem(String id) {
		for (Employee employee : DataStore.employeeList) {
			if (employee.getId().equals(id)) {
				System.out.println(employee.toString());
				return;
			}
		}
		System.out.println("khong tim thay nhan vien trong ds. ");
	}

	@Override
	public void xuat() {
		if (DataStore.employeeList.isEmpty()) {
			System.out.println("ds trong ");
		} else {
			for (Employee employee : DataStore.employeeList) {
				System.out.println(employee.toString());
			}
			System.out.println("So luong nhan vien trong ds: " + Employee.getTotalEmployees());
		}
	}

	public void readfile(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				String id = data[0];
				String name = data[1];
				String phone = data[2];
				String position = data[3];
				double salary = Double.parseDouble(data[4]);
				DataStore.employeeList.add(new Employee(id, name, phone, position, salary));
			}
			System.out.println("Doc file thanh cong");
		} catch (IOException e) {
			System.out.println("Error reading product file: " + e.getMessage());
		}

	}

}
