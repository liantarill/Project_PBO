/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class Bullet {

    private final List<ImageView> bullets = new ArrayList<>();
    private final ImageView Hero;
    private final AnchorPane scene;
    private final FXMLDocumentController controller;

    private boolean canShoot = true;
    private final long shootDelay = 200;

    public Bullet(ImageView Hero, AnchorPane scene, FXMLDocumentController controller) {
        this.Hero = Hero;
        this.scene = scene;
        this.controller = controller;
    }

    public void fireBullet() {
        ImageView bullet = new ImageView(new Image(getClass().getResource("img/bullet1.png").toExternalForm()));
        bullet.setFitWidth(10);
        bullet.setFitHeight(20);

        bullet.setLayoutX(Hero.getLayoutX() + Hero.getFitWidth() / 2 - bullet.getFitWidth() / 2);
        bullet.setLayoutY(Hero.getLayoutY() - bullet.getFitHeight());

        scene.getChildren().add(bullet);
        bullets.add(bullet);

        animateBullet(bullet);
    }

    public void fireBulletWithDelay() {
        if (!canShoot)
            return;
        fireBullet();
        canShoot = false;

        Timeline delay = new Timeline(new KeyFrame(Duration.millis(shootDelay), e -> canShoot = true));
        delay.play();
    }

    public void animateBullet(ImageView bullet) {
        double speed = 5.0;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                bullet.setLayoutY(bullet.getLayoutY() - speed);
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

    public void checkBulletCollision() {
        Iterator<ImageView> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            ImageView bullet = bulletIterator.next();

            Iterator<ImageView> enemyIterator = controller.enemy.enemies.iterator();
            while (enemyIterator.hasNext()) {
                ImageView enemy = enemyIterator.next();

                if (bullet.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                    controller.enemy.createExplosionEffect(enemy.getLayoutX(), enemy.getLayoutY());

                    scene.getChildren().remove(bullet);
                    scene.getChildren().remove(enemy);

                    bulletIterator.remove();
                    enemyIterator.remove();

                    controller.score += 10;
                    controller.updateScore();
                    return;
                }
            }
        }
    }
}
