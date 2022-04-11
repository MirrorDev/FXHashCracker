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
        try {
            if (txtFieldInput.getText() != null && txtFieldInput.getText() != "") {
                String word = txtFieldInput.getText();
                //URL wordsURL = getClass().getResource("./words.txt");
                //URL wordsURL = new File(".\\src\\main\\resources\\words.txt").toURI().toURL();
                // None of those work :|
                List<List<String>> words = CrackDict.getWordsFromFileSplit(wordsURL.toString());
                List<String> wordsOne = words.get(0);
                List<String> wordsTwo = words.get(1);
                ExecutorService service = Executors.newFixedThreadPool(2);
                Future<Object> future1 = (Future<Object>) service.submit(new Thread(new ThreadedWords(wordsOne, word)));
                Future<Object> future2 = (Future<Object>) service.submit(new Thread(new ThreadedWords(wordsTwo, word)));

                while (!future1.isDone() && !future2.isDone()) {
                    txtStatus.setVisible(true);
                    txtStatus.setText("Calculating...");
                }

                txtStatus.setText("Evaluating...");
                var result1Object = future1.get();
                var result2Object = future2.get();
                txtAreaSysOut.setText(result1Object.toString());

                /*
                Thread threadOne = new Thread(new ThreadedWords(wordsOne, word));
                Thread threadTwo = new Thread(new ThreadedWords(wordsTwo, word));
                var result = threadOne.get;
                threadTwo.start();

                threadOne.join();
                threadTwo.join();
                threadOne.

                 */
            } else {
                throw new NullPointerException();
            }
        } catch (IOException e) {
            txtAreaSysOut.clear();
            txtAreaSysOut.setText(e.toString());
            txtStatus.setVisible(true);
            txtStatus.setText("IOException occurred!");
            txtAreaSysOut.setText(e.toString());
        } catch (InterruptedException e) {
            txtAreaSysOut.clear();
            txtAreaSysOut.setText(e.toString());
            txtStatus.setVisible(true);
            txtStatus.setText("InterruptedException occurred!");
            txtAreaSysOut.setText(e.toString());
        } catch (ExecutionException e) {
            txtAreaSysOut.setText(e.toString());
            txtAreaSysOut.clear();
            txtStatus.setVisible(true);
            txtStatus.setText("ExecutionException occurred!");
            txtAreaSysOut.setText(e.toString());
        } catch (NullPointerException e) {
            txtAreaSysOut.clear();
            txtStatus.setVisible(true);
            txtStatus.setText("Please fill out all Arguments!");
            txtAreaSysOut.setText(e.toString());
        }  catch (Exception e) {
            txtAreaSysOut.setText(e.toString());
            txtAreaSysOut.clear();
            txtStatus.setVisible(true);
            txtStatus.setText("Execption occurred (see Console Output)!");
            txtAreaSysOut.setText(e.toString());
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
