package hr.management.sarab.controller;

import hr.management.sarab.database.AdminDatabase;
import hr.management.sarab.database.EmployeeDatabase;
import hr.management.sarab.model.Admin;
import hr.management.sarab.model.Controller;
import hr.management.sarab.model.Employee;
import hr.management.sarab.model.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Map;
import java.util.Optional;

public class AdminController extends Controller {

    @FXML
    private TableView<Admin> admin_table;
    @FXML
    private TableColumn<Admin, String> tc_fname, tc_lname, tc_username, tc_department;
    @FXML
    private TableColumn<Admin, Double> tc_salary;
    @FXML
    private TextField f_id, f_fname, f_lname, f_username, f_salary, f_department;
    private AdminDatabase db;
    private Admin oldAdmin;

    @Override
    public void init() {
        db = new AdminDatabase();
        tc_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tc_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tc_department.setCellValueFactory(new PropertyValueFactory<>("department"));
        admin_table.getSelectionModel().selectedItemProperty().addListener((obs, old, current) -> {
            if (current != null) {
                Admin admin = admin_table.getSelectionModel().getSelectedItem();
                if (admin != null) {
                    oldAdmin = admin;
                    f_id.setText(String.valueOf(admin.getId()));
                    f_fname.setText(admin.getFirstName());
                    f_lname.setText(admin.getLastName());
                    f_username.setText(admin.getUsername());
                    f_salary.setText(String.valueOf(admin.getSalary()));
                    f_department.setText(admin.getDepartment());
                }
            }
        });
        oldAdmin = null;
        updateAdmins();
    }

    private void updateAdmins() {
        Map<Integer, Admin> admins = db.readAdmins();
        ObservableList<Admin> adminList = FXCollections.observableArrayList(admins.values());
        admin_table.setItems(adminList);
    }

    @FXML
    private void insertAdmin() {
        String firstName = f_fname.getText().trim();
        String lastName = f_lname.getText().trim();
        String username = f_username.getText().trim();
        String salaryStr = f_salary.getText().trim();
        String department = f_department.getText().trim();
        TextField[] fields = {f_fname, f_lname, f_username, f_salary, f_department};
        for (TextField field : fields) {
            if (field.getText().trim().isBlank()) {
                showAlert("Empty Field", "No any field should be empty!");
                return;
            }
        }
        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            showAlert("Incorrect data", "Salary should have valid data.");
            return;
        }
        Admin admin = new Admin(0, firstName, lastName, salary, username, department);
        db.insert(admin);
        for (TextField field : fields) {
            field.clear();
        }
        updateAdmins();
    }

    @FXML
    private void updateAdmin() {
        if (oldAdmin == null) {
            showAlert("Update", "Select the row from table.");
            return;
        }
        String firstName = f_fname.getText().trim();
        String lastName = f_lname.getText().trim();
        String username = f_username.getText().trim();
        String salaryStr = f_salary.getText().trim();
        String department = f_department.getText().trim();
        TextField[] fields = {f_id, f_fname, f_lname, f_username, f_salary, f_department};
        for (TextField field : fields) {
            if (field.getText().trim().isBlank()) {
                showAlert("Empty Field", "No any field should be empty!");
                return;
            }
        }
        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            showAlert("Incorrect data", "Salary should have valid data.");
            return;
        }
        Admin admin = new Admin(oldAdmin.getId(), firstName, lastName, salary, username, department);
        db.update(oldAdmin.getId(), admin);
        oldAdmin = null;
        for (TextField field : fields) {
            field.clear();
        }
        updateAdmins();
    }

    @FXML
    private void deleteAdmin() {
        if (oldAdmin == null) {
            showAlert("Delete", "Select the row from table.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you confirmed to delete it?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            db.delete(oldAdmin.getId());
            oldAdmin = null;
            updateAdmins();
            TextField[] fields = {f_id, f_fname, f_lname, f_username, f_salary, f_department};
            for (TextField field : fields) {
                field.clear();
            }
            showAlert("Done", "Admin is deleted!");
        }
    }

    @FXML
    public void backEvent(){
        this.getUpdator().setScene(View.DASHBOARD);
    }

}