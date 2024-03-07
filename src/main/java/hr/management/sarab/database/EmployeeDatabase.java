package hr.management.sarab.database;

import hr.management.sarab.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDatabase {

	// Attributes.
	private Database database;

	public EmployeeDatabase() {
		this.database = Database.getInstance();
	}

	public void insert(Employee emp) {
		database.execute("insert into employee (first_name, last_name, contact, salary, streetaddress) values ('" + emp.getFirstName()
				+ "', '" + emp.getLastName() + "', '" + emp.getContact() + "', " + emp.getSalary() + ", '"+emp.getStreetAddress()+"')");
	}

	public Map<Integer, Employee> readEmployees() {
		Map<Integer, Employee> employees = new HashMap<>();
		ResultSet result = database.executeQuery("select * from employee");
		try {
			while (result.next()) {
				int id = result.getInt("emp_id");
				employees.put(id, new Employee(id, result.getString("first_name"), result.getString("last_name"),
						result.getDouble("salary"), result.getString("contact"),
						result.getString("streetaddress")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public void update(int empId, Employee employee) {
		database.execute("update employee set first_name = '" + employee.getFirstName() + "', last_name = '"
				+ employee.getLastName() + "', contact = '" + employee.getContact() + "', salary = "
				+ employee.getSalary() + ", streetaddress = '"+employee.getStreetAddress()+"' where emp_id = " + empId);
	}

	public void delete(int empId) {
		database.execute("delete from employee where emp_id = " + empId);
	}
}
