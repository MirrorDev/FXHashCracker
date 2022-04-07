package presentation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import runtime.RuntimeLauncher;
import runtime.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class SceneControllerDict implements Initializable {

    @FXML
    private Button bntBackToMain;

    @FXML
    private Button bntExit;

    @FXML
    private TextArea txtAreaSysOut;

    @FXML
    private Text txtConsole;

    @FXML
    private TextField txtFieldInput;

    @FXML
    private TextField txtFiledResult;

    @FXML
    private Text txtInput;

    @FXML
    private Text txtResult;

    @FXML
    private Text txtTitle;

    @FXML
    private Text txtTitle1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bntBackToMain.setOnAction(event -> {
            try {
                URL urlForDictCracker = getClass().getClassLoader().getResource("layoutMain.fxml").toURI().toURL();
                bntBackToMain.getScene().getWindow().hide();
                SceneSwitcher switcher = new SceneSwitcher(urlForDictCracker);
                Stage root = new Stage();
                root.setTitle("SHA-256-Crackers");
                switcher.start(root);
            } catch (Exception e) {
                System.out.println(e);
                System.exit(-1);
            }
        });

        bntExit.setOnAction(event -> System.exit(0));
    }
}
