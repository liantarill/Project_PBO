/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class Enemy {

    private final AnchorPane scene;
    private final ImageView myHero;
    private final List<ImageView> enemies = new ArrayList<>();
    private final List<AnimationTimer> activeTimers = new ArrayList<>();
    private final Timeline spawner;
    private final Random random = new Random();
    private MainPageController controller;

    musicPlayer explosive = new musicPlayer();

    public Enemy(AnchorPane scene, ImageView myHero, MainPageController controller) {
        this.scene = scene;
        this.myHero = myHero;
        this.controller = controller;

        spawner = new Timeline(new KeyFrame(Duration.seconds(1), event -> spawnEnemy()));
        spawner.setCycleCount(Timeline.INDEFINITE);
    }

    public List<ImageView> getEnemies() {
        return enemies;
    }

    public void startEnemySpawner() {
        spawner.play();
    }

    public void spawnEnemy() {
        String[] enemyImages = {
                getClass().getResource("img/enemy/enemy1.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy2.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy3.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy4.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy5.png").toExternalForm(),
                getClass().getResource("img/enemy/enemy6.png").toExternalForm()
        };

        int index = random.nextInt(enemyImages.length);
        double x = random.nextDouble() * (scene.getWidth() - 50);

        ImageView enemy = new ImageView(new Image(enemyImages[index]));
        enemy.setFitWidth(50);
        enemy.setFitHeight(50);
        enemy.setLayoutX(x);
        enemy.setLayoutY(-50);

        scene.getChildren().add(enemy);
        enemies.add(enemy);
        createEnemyMovement(enemy);
    }

    public void createEnemyMovement(ImageView enemy) {
        double speed = 2.0;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                enemy.setLayoutY(enemy.getLayoutY() + speed);

                if (enemies.contains(enemy) && enemy.getBoundsInParent().intersects(myHero.getBoundsInParent())) {
                    enemies.remove(enemy);
                    controller.hero.triggerHeroBeepEffect();
                    controller.hero.handleHeroDamage();
                    MainPageController.setScore(MainPageController.getScore() - 10);
                    controller.updateScore();
                    // stop();
                    if (enemy.getLayoutY() > scene.getHeight()) {
                        scene.getChildren().remove(enemy);
                        enemies.remove(enemy);
                        stop();
                    }
                    return;
                }

                if (enemies.contains(enemy) && enemy.getLayoutY() > scene.getHeight()) {
                    scene.getChildren().remove(enemy);
                    enemies.remove(enemy);
                    MainPageController.setScore(MainPageController.getScore() - 10);
                    controller.updateScore();
                    stop();
                }
            }
        };

        timer.start();
        activeTimers.add(timer); // Add the timer to the list of active timers

        if (controller.hero.getHealt() <= 0) {
            timer.stop();
        }
    }

    public void createExplosionEffect(double x, double y) {
        ImageView explosion = new ImageView();
        explosion.setFitWidth(50);
        explosion.setFitHeight(50);
        explosion.setLayoutX(x);
        explosion.setLayoutY(y);

        scene.getChildren().add(explosion);
        explosive.explosionSFX();

        String[] explosionFrames = {
                getClass().getResource("img/explosion/explosion1.png").toExternalForm(),
                getClass().getResource("img/explosion/explosion2.png").toExternalForm(),
                getClass().getResource("img/explosion/explosion3.png").toExternalForm(),
                getClass().getResource("img/explosion/explosion4.png").toExternalForm(),
                getClass().getResource("img/explosion/explosion5.png").toExternalForm(),
                getClass().getResource("img/explosion/explosion6.png").toExternalForm(),
                getClass().getResource("img/explosion/explosion7.png").toExternalForm()
        };

        Timeline explosionAnimation = new Timeline();

        for (int i = 0; i < explosionFrames.length; i++) {
            final int frameIndex = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(100 * i), event -> {
                explosion.setImage(new Image(explosionFrames[frameIndex]));
            });
            explosionAnimation.getKeyFrames().add(keyFrame);
        }

        explosionAnimation.setOnFinished(event -> scene.getChildren().remove(explosion));

        explosionAnimation.play();
    }

    public void stopAllTimers() {
        spawner.stop();

        for (AnimationTimer timer : activeTimers) {
            timer.stop();
        }
        activeTimers.clear();
    }
}