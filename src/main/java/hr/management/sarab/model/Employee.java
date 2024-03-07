package hr.management.sarab.model;

public class Employee extends Person {

    private String contact;
    private String streetAddress;

    public Employee(int id, String firstName, String lastName, double salary, String contact, String streetAddress) {
        super(id, firstName, lastName, salary);
        this.contact = contact;
        this.streetAddress = streetAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
