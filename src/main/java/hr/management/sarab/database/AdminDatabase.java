package hr.management.sarab.database;

import hr.management.sarab.model.Admin;
import hr.management.sarab.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AdminDatabase {

	// Attributes.
	private Database database;

	public AdminDatabase() {
		this.database = Database.getInstance();
	}

	public void insert(Admin admin) {
		database.execute("insert into admin (first_name, last_name, username, salary, department) values ('" + admin.getFirstName()
				+ "', '" + admin.getLastName() + "', '" + admin.getUsername() + "', " + admin.getSalary() + ", '"+admin.getDepartment()+"')");
	}

	public Map<Integer, Admin> readAdmins() {
		Map<Integer, Admin> admins = new HashMap<>();
		ResultSet result = database.executeQuery("select * from admin");
		try {
			while (result.next()) {
				int id = result.getInt("admin_id");
				admins.put(id, new Admin(id, result.getString("first_name"), result.getString("last_name"),
						result.getDouble("salary"), result.getString("username"),
						result.getString("department")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;
	}

	public void update(int adminId, Admin admin) {
		database.execute("update admin set first_name = '" + admin.getFirstName() + "', last_name = '"
				+ admin.getLastName() + "', username = '" + admin.getUsername() + "', salary = "
				+ admin.getSalary() + ", department = '"+admin.getDepartment()+"' where admin_id = " + adminId);
	}

	public void delete(int empId) {
		database.execute("delete from admin where admin_id = " + empId);
	}
}
