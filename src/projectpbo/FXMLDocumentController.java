/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane scene;

    @FXML
    private ImageView Hero;

    private final Random random = new Random();
    private final List<ImageView> bullets = new ArrayList<>();
    private final List<ImageView> enemies = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scene.setOnKeyPressed(this::Movement);

        scene.requestFocus();
        scene.setOnMouseClicked(event -> scene.requestFocus());

        startEnemySpawner();
    }

    @FXML
    private void Movement(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if (Hero.getLayoutY() > 0) {
                    Hero.setLayoutY(Hero.getLayoutY() - 10);
                }
                break;
            case S:
                if (Hero.getLayoutY() < scene.getHeight() - Hero.getFitHeight()) {
                    Hero.setLayoutY(Hero.getLayoutY() + 10);
                }
                break;
            case D:
                if (Hero.getLayoutX() < scene.getWidth() - Hero.getFitWidth()) {
                    Hero.setLayoutX(Hero.getLayoutX() + 10);
                }
                break;
            case A:
                if (Hero.getLayoutX() > 0) {
                    Hero.setLayoutX(Hero.getLayoutX() - 10);
                }
                break;
            case SPACE:
                fireBulletWithDelay();
                break;
        }
    }

    private void startEnemySpawner() {
        Timeline spawner = new Timeline(new KeyFrame(Duration.seconds(1), event -> spawnEnemy()));
        spawner.setCycleCount(Timeline.INDEFINITE);
        spawner.play();
    }

    private void spawnEnemy() {
        String[] enemyImages = {
                getClass().getResource("img/enemy1.png").toExternalForm(),
                getClass().getResource("img/enemy2.png").toExternalForm(),
                getClass().getResource("img/enemy3.png").toExternalForm(),
                getClass().getResource("img/enemy4.png").toExternalForm(),
                getClass().getResource("img/enemy5.png").toExternalForm(),
                getClass().getResource("img/enemy6.png").toExternalForm()
        };

        int randomIndex = random.nextInt(enemyImages.length);
        String selectedImagePath = enemyImages[randomIndex];

        double randomX = random.nextDouble() * (scene.getWidth() - 50);
        double startY = -50;

        ImageView enemy = new ImageView(new Image(selectedImagePath));
        enemy.setFitWidth(50);
        enemy.setFitHeight(50);
        enemy.setLayoutX(randomX);
        enemy.setLayoutY(startY);

        scene.getChildren().add(enemy);
        enemies.add(enemy);

        createEnemyMovement(enemy);
    }

    private void createEnemyMovement(ImageView enemy) {
        double speed = 2.0;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                enemy.setLayoutY(enemy.getLayoutY() + speed);

                // Check for collisions
                checkBulletCollision();

                if (enemy.getLayoutY() > scene.getHeight()) {
                    scene.getChildren().remove(enemy);
                    enemies.remove(enemy);
                    stop();
                }
            }
        };

        timer.start();
    }

    private void fireBullet() {
        ImageView bullet = new ImageView(new Image(getClass().getResource("img/bullet1.png").toExternalForm()));
        bullet.setFitWidth(10);
        bullet.setFitHeight(20);

        bullet.setLayoutX(Hero.getLayoutX() + Hero.getFitWidth() / 2 - bullet.getFitWidth() / 2); // pas di tengah
        bullet.setLayoutY(Hero.getLayoutY() - bullet.getFitHeight()); // untuk di atas hero

        scene.getChildren().add(bullet);
        bullets.add(bullet);

        animateBullet(bullet);
    }

    private boolean canShoot = true;
    private final long shootDelay = 200;

    private void fireBulletWithDelay() {
        if (!canShoot)
            return;

        fireBullet();
        canShoot = false;

        Timeline delay = new Timeline(new KeyFrame(Duration.millis(shootDelay), e -> canShoot = true));
        delay.setCycleCount(1);
        delay.play();
    }

    private void animateBullet(ImageView bullet) {
        double speed = 5.0;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.setLayoutY(bullet.getLayoutY() - speed);

                // Check for collisions
                checkBulletCollision();

                if (bullet.getLayoutY() < -bullet.getFitHeight()) {
                    scene.getChildren().remove(bullet);
                    bullets.remove(bullet);
                    stop();
                }
            }
        };

        timer.start();
    }

    private void checkBulletCollision() {
        Iterator<ImageView> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            ImageView bullet = bulletIterator.next();

            Iterator<ImageView> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                ImageView enemy = enemyIterator.next();

                if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    scene.getChildren().remove(bullet);
                    scene.getChildren().remove(enemy);

                    bulletIterator.remove();
                    enemyIterator.remove();

                    // nanti ditampah score sama effect
                    return;
                }
            }
        }
    }
}
