package hr.management.sarab.model;

public enum View {

    LOGIN("login.fxml"),
    EMPLOYEE_CRUD("employee_view.fxml"),
    ADMIN_CRUD("admin_view.fxml"),
    DASHBOARD("dashboard.fxml");

    private String path;

    View(String path){
        this.path = path;
    }

    @Override
    public String toString(){
        return path;
    }

}
