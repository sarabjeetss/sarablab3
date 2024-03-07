package hr.management.sarab.controller;

import hr.management.sarab.database.UserDatabase;
import hr.management.sarab.model.Controller;
import hr.management.sarab.model.View;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController extends Controller {

    @FXML
    private TextField login_username;
    @FXML
    private PasswordField login_password;
    private UserDatabase database;

    @Override
    public void init() {
        database = new UserDatabase();
    }

    @FXML
    private void login(){
        String username = login_username.getText().trim();
        String password = login_password.getText().trim();
        if (username.isBlank() || password.isBlank()){
            showAlert("Login", "No any field should be empty!");
        } else {
            if (database.canLogin(username, password)) {
                this.getUpdator().setScene(View.DASHBOARD);
            } else{
                showAlert("Login", "Username or Password is wrong!");
            }
        }
    }

}
