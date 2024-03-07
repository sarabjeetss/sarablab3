module hr.management.sarab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens hr.management.sarab to javafx.fxml;
    exports hr.management.sarab;
    opens hr.management.sarab.controller to javafx.fxml;
    opens hr.management.sarab.model to javafx.base;

}