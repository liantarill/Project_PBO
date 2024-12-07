/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projectpbo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class GameOverPageController implements Initializable {

    @FXML
    private ImageView yesBtn;
    @FXML
    private ImageView noBtn;
    @FXML
    private Text scored;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void backToGame(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = (Stage) scored.getScene().getWindow();
        stage.close();

        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        stage.show();

        root.requestFocus();
    }

    @FXML
    private void closeGame(MouseEvent event) {
        javafx.application.Platform.exit();
    }

    public void setScore(int score) {
        scored.setText("" + score);
    }
}
