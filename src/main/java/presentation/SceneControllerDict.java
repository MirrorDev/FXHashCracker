package presentation;

import crackers.dictonaryCracker.DictonaryCrackerV2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import runtime.SceneSwitcher;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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
        DictonaryCrackerV2 dictonaryCrackerV2 = new DictonaryCrackerV2(txtFieldInput.getText(),
                                                                    // insert file location here
                                                                            "SHA-256");
        try {
            List<List<String>> hashed = dictonaryCrackerV2.crackHash();
            List<String> control = null;
            txtAreaSysOut.setText(dictonaryCrackerV2.getResults(hashed));
            for (Iterator<List<String>> lstIterator = hashed.iterator(); lstIterator.hasNext();) {
                control = lstIterator.next();
                if (control.get(0).contains("Thread")) {
                    continue;
                } else {
                    txtResult.setText(control.get(2));
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        } catch (InterruptedException e) {
            System.out.println(e);
            System.exit(-1);
        }

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
