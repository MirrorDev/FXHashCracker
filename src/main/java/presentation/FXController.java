package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXController extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layoutMain.fxml").toURI().toURL());
        stage.setScene(new Scene(root));
        stage.setTitle("SHA-256-Crackers");
        stage.show();
    }
}
