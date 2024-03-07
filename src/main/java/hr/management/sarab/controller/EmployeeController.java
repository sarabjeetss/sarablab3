package hr.management.sarab.controller;

import hr.management.sarab.database.EmployeeDatabase;
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

public class EmployeeController extends Controller {

    @FXML
    private TableView<Employee> employee_table;
    @FXML
    private TableColumn<Employee, String> tc_fname, tc_lname, tc_contact, tc_street;
    @FXML
    private TableColumn<Employee, Double> tc_salary;
    @FXML
    private TextField f_id, f_fname, f_lname, f_contact, f_salary, f_street;
    private EmployeeDatabase db;
    private Employee oldEmp;

    @Override
    public void init() {
        db = new EmployeeDatabase();
        tc_fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tc_lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tc_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tc_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tc_street.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));
        employee_table.getSelectionModel().selectedItemProperty().addListener((obs, old, current) -> {
            if (current != null) {
                Employee emp = employee_table.getSelectionModel().getSelectedItem();
                if (emp != null) {
                    oldEmp = emp;
                    f_id.setText(String.valueOf(emp.getId()));
                    f_fname.setText(emp.getFirstName());
                    f_lname.setText(emp.getLastName());
                    f_contact.setText(emp.getContact());
                    f_salary.setText(String.valueOf(emp.getSalary()));
                    f_street.setText(emp.getStreetAddress());
                }
            }
        });
        oldEmp = null;
        updateEmployees();
    }

    private void updateEmployees() {
        Map<Integer, Employee> employees = db.readEmployees();
        ObservableList<Employee> employeeList = FXCollections.observableArrayList(employees.values());
        employee_table.setItems(employeeList);
    }

    @FXML
    private void insertEmployee() {
        String firstName = f_fname.getText().trim();
        String lastName = f_lname.getText().trim();
        String contact = f_contact.getText().trim();
        String salaryStr = f_salary.getText().trim();
        String street = f_street.getText().trim();
        TextField[] fields = {f_fname, f_lname, f_contact, f_salary, f_street};
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
        Employee employee = new Employee(0, firstName, lastName, salary, contact, street);
        db.insert(employee);
        for (TextField field : fields) {
            field.clear();
        }
        updateEmployees();
    }

    @FXML
    private void updateEmployee() {
        if (oldEmp == null) {
            showAlert("Update", "Select the row from table.");
            return;
        }
        String firstName = f_fname.getText().trim();
        String lastName = f_lname.getText().trim();
        String contact = f_contact.getText().trim();
        String salaryStr = f_salary.getText().trim();
        String street = f_street.getText().trim();
        TextField[] fields = {f_id, f_fname, f_lname, f_contact, f_salary, f_street};
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
        Employee employee = new Employee(oldEmp.getId(), firstName, lastName, salary, contact, street);
        db.update(oldEmp.getId(), employee);
        oldEmp = null;
        for (TextField field : fields) {
            field.clear();
        }
        updateEmployees();
    }

    @FXML
    private void deleteEmployee() {
        if (oldEmp == null) {
            showAlert("Delete", "Select the row from table.");
            return;
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you confirmed to delete it?");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            db.delete(oldEmp.getId());
            oldEmp = null;
            updateEmployees();
            TextField[] fields = {f_id, f_fname, f_lname, f_contact, f_salary, f_street};
            for (TextField field : fields) {
                field.clear();
            }
            showAlert("Done", "Employee is deleted!");
        }
    }

    @FXML
    public void backEvent(){
        this.getUpdator().setScene(View.DASHBOARD);
    }

}