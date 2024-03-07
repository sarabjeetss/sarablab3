package hr.management.sarab;

import hr.management.sarab.model.Controller;
import hr.management.sarab.model.UIUpdator;
import hr.management.sarab.model.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HRApplication extends Application implements UIUpdator {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("HR Application");
        setScene(View.LOGIN);
        stage.show();
    }

    public static void main(String[] args) {

        launch(HRApplication.class);

    }

    @Override
    public void setScene(View view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view.toString()));
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.setUpdator(this);
            controller.init();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
