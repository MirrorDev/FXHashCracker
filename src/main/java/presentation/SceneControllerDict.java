package presentation;

import crackers.CrackDict;
import javafx.event.ActionEvent;
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
import runtime.ThreadedWords;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SceneControllerDict implements Initializable {


    @FXML
    private Button bntBackToMain;

    @FXML
    private Button bntClear;

    @FXML
    private Button bntExit;

    @FXML
    private Button bntSubmit;

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

    @FXML
    private TextField txtStatus;

    @FXML
    void clearTxt(ActionEvent event) {
        txtFieldInput.clear();
        txtFiledResult.clear();
        txtAreaSysOut.clear();
        txtStatus.clear();
        txtStatus.setVisible(false);
    }

    @FXML
    void submitTxt(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bntBackToMain.setOnAction(event -> {
            try {
                URL urlForDictCracker = getClass().getClassLoader().getResource("layoutMain.fxml").toURI().toURL();
                SceneSwitcher switcher = new SceneSwitcher(urlForDictCracker);
                Stage root = new Stage();
                root.setTitle("SHA-256-Crackers");
                switcher.start(root);
                Stage stage = (Stage) bntBackToMain.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                System.out.println(e);
                System.exit(-1);
            }
        });

        bntExit.setOnAction(event -> System.exit(0));
    }
}
