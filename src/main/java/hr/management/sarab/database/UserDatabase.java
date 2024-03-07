package hr.management.sarab.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabase {

    // Attributes.
    private Database database;

    public UserDatabase() {
        this.database = Database.getInstance();
    }

    public boolean canLogin(String username, String password) {
        ResultSet result = database.executeQuery("select username,password from users where username='" + username
                + "' and" + " password='"+password+"';");
        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
