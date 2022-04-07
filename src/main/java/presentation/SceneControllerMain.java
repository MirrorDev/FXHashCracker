package presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import runtime.SceneSwitcher;

public class SceneControllerMain implements Initializable {

    @FXML
    private Button bntDate;

    @FXML
    private Button bntDict;

    @FXML
    private Text titleText;

    @FXML
    private Button bntExit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bntDict.setOnAction(event -> {
            try {
                URL urlForDictCracker = getClass().getClassLoader().getResource("layoutDictCracker.fxml").toURI().toURL();
                bntDict.getScene().getWindow().hide();
                SceneSwitcher switcher = new SceneSwitcher(urlForDictCracker);
                Stage root = new Stage();
                root.setTitle("Dictionary-Hash-Cracker (SHA-256)");
                switcher.start(root);
            } catch (Exception e) {
                System.exit(-1);
            }
        });

        bntDate.setOnAction(event -> {
            try {
                URL urlForDateCracker = getClass().getClassLoader().getResource("layoutDateCracker.fxml").toURI().toURL();
                bntDate.getScene().getWindow().hide();
                Stage root = new Stage();
                SceneSwitcher switcher = new SceneSwitcher(urlForDateCracker);
                root.setTitle("Date-Hash-Cracker (SHA-256)");
                switcher.start(root);
            } catch (Exception e) {
                System.out.println(e);
                System.exit(-1);
            }
        });

        bntExit.setOnAction(event -> System.exit(0));
    }
}
