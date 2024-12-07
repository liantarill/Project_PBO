/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projectpbo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StartPageController implements Initializable {

    @FXML
    private ImageView startBtn;
    @FXML
    private ImageView sceneStart;
    @FXML
    private AnchorPane scene;
    private Random random = new Random();
    private final List<ImageView> enemies = new ArrayList<>();
    musicPlayer music = new musicPlayer();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        music.startMusic();
        startEnemySpawner();

    }

    @FXML
    private void startGame(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage) startBtn.getScene().getWindow();
        stage.setTitle("Space War");
        Scene newScene = new Scene(root);

        stage.setScene(newScene);
        stage.show();
        music.stopMusic();
        root.requestFocus();

    }

    private void startEnemySpawner() {
        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(1), event -> spawnEnemy()));
        spawner.setCycleCount(Timeline.INDEFINITE);
        spawner.play();
    }

    private void spawnEnemy() {
        String[] enemyImages = {
                getClass().getResource("img/enemy/enemy1.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy2.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy3.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy4.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy5.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy6.png").toExternalForm()
        };

        int randomIndex = random.nextInt(enemyImages.length);
        String selectedImagePath = enemyImages[randomIndex];

        boolean spawnFromLeft = random.nextBoolean();
        double startX = spawnFromLeft ? -50 : scene.getWidth() + 50;
        double randomY = random.nextDouble() * (scene.getHeight() - 50);

        ImageView enemy = new ImageView(new Image(selectedImagePath));
        enemy.setFitWidth(50);
        enemy.setFitHeight(50);
        enemy.setLayoutX(startX);
        enemy.setLayoutY(randomY);

        if (!spawnFromLeft)
            enemy.setRotate(90);
        else
            enemy.setRotate(270);

        scene.getChildren().add(enemy);
        enemies.add(enemy);

        createEnemyMovement(enemy, spawnFromLeft);
    }

    private void createEnemyMovement(ImageView enemy, boolean spawnFromLeft) {
        double speed = 10.0;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (spawnFromLeft) {
                    enemy.setLayoutX(enemy.getLayoutX() + speed);
                } else {
                    enemy.setLayoutX(enemy.getLayoutX() - speed);
                }

                if (enemy.getLayoutX() > scene.getWidth() + 50 || enemy.getLayoutX() < -50) {
                    scene.getChildren().remove(enemy);
                    enemies.remove(enemy);
                    stop();
                }
            }
        };

        timer.start();

    }

}
