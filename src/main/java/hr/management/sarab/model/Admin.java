package hr.management.sarab.model;

public class Admin extends Person {

    private String username;
    private String department;

    public Admin(int id, String firstName, String lastName, double salary, String username, String department) {
        super(id, firstName, lastName, salary);
        this.username = username;
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
