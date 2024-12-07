/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane scene;
    @FXML
    private ImageView Hero;
    @FXML
    public ImageView health1;
    @FXML
    public ImageView health4;
    @FXML
    public ImageView health3;
    @FXML
    public ImageView health2;
    @FXML
    public ImageView health5;
    @FXML
    private Text point;

    public static int score = 0;
    public final Random random = new Random();

    Bullet bullet;
    Enemy enemy;
    Hero hero;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bullet = new Bullet(Hero, scene, this);
        enemy = new Enemy(scene, Hero, this);
        hero = new Hero(scene, Hero, this);

        scene.setOnKeyPressed(this::Movement);
        enemy.startEnemySpawner();
    }

    @FXML
    public void Movement(KeyEvent event) {
        hero.heroMovement(event.getCode());
    }

    public void updateScore() {
        point.setText("" + score);
    }

    public void gameOver() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameOverPage.fxml"));
        Parent root = loader.load();

        Stage currentStage = (Stage) scene.getScene().getWindow();
        currentStage.close();

        GameOverPageController controller = loader.getController();
        controller.setScore(score);

        Stage stage = (Stage) scene.getScene().getWindow();
        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        stage.show();

        root.requestFocus();
        enemy.enemies.clear();

    }

}
