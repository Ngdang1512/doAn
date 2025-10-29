package doAn;

abstract class Person {
    private String id;
    private String name;
    private String sdt;

    public Person(String id, String name, String sdt) {
        this.id = id;
        this.name = name;
        this.sdt = sdt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return sdt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String sdt) {
        this.sdt = sdt;
    }

    public abstract void displayInfo();

    // Ghi đè toString() để trả về thông tin chung của Person
    @Override
    public String toString() {
        return "ID: " + id + ", Ten: " + name + ", Phone : " + sdt;
    }
}
