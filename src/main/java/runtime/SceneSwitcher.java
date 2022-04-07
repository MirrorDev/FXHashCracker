package runtime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class SceneSwitcher extends Application {

    private Stage stage;
    private URL url;

    public SceneSwitcher(URL url) {
        this.url = url;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        Parent newRoot = FXMLLoader.load(getUrl());
        this.stage.setScene(new Scene(newRoot));
        this.stage.show();
    }

    public URL getUrl() {
        return url;
    }
}
