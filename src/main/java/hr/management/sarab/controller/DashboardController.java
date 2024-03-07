package hr.management.sarab.controller;

import hr.management.sarab.model.Controller;
import hr.management.sarab.model.View;
import javafx.fxml.FXML;

public class DashboardController extends Controller {

    @Override
    public void init() {

    }

    @FXML
    private void adminEvent(){
        this.getUpdator().setScene(View.ADMIN_CRUD);
    }

    @FXML
    private void employeeEvent(){
        this.getUpdator().setScene(View.EMPLOYEE_CRUD);
    }

    @FXML
    private void logoutEvent(){
        this.getUpdator().setScene(View.LOGIN);
    }

    @FXML
    private void exitEvent(){
        System.exit(0);
    }

}
