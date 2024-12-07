/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author LENOVO
 */
public class Hero implements IHero {
    private AnchorPane scene;
    private ImageView Hero;
    private double heroX;
    private double heroY;
    private MainPageController controller;
    private static int health;

    public Hero(AnchorPane scene, ImageView Hero, MainPageController controller) {
        this.scene = scene;
        this.Hero = Hero;
        this.controller = controller;
        health = 5;
    }

    public double getHeroX() {
        return Hero.getLayoutX();
    }

    public void setHeroX(double X) {
        Hero.setLayoutX(X);
        heroX = Hero.getLayoutX();
    }

    public double getHeroY() {
        return Hero.getLayoutY();
    }

    public void setHeroY(double Y) {
        Hero.setLayoutY(Y);
        heroY = Hero.getLayoutY();
    }

    public static int getHealt() {
        return health;
    }

    @Override
    public void heroMovement() {
        if (controller.getPressedKeys().contains(KeyCode.W)) {
            if (getHeroY() > 0) {
                setHeroY(getHeroY() - 10);
            }
        }
        if (controller.getPressedKeys().contains(KeyCode.S)) {
            if (getHeroY() < scene.getHeight() - Hero.getFitHeight()) {
                setHeroY(getHeroY() + 10);
            }
        }
        if (controller.getPressedKeys().contains(KeyCode.D)) {
            if (getHeroX() < scene.getWidth() - Hero.getFitWidth()) {
                Hero.setLayoutX(getHeroX() + 10);
            }
        }
        if (controller.getPressedKeys().contains(KeyCode.A)) {
            if (getHeroX() > 0) {
                setHeroX(getHeroX() - 10);
            }
        }
        if (controller.getPressedKeys().contains(KeyCode.SPACE)) {
            controller.bullet.fireBulletWithDelay();
        }
    }

    @Override
    public void updateHealthUI() {
        ImageView[] healthBars = {
                controller.health1,
                controller.health2,
                controller.health3,
                controller.health4,
                controller.health5 };
        for (int i = 0; i < healthBars.length; i++) {
            healthBars[i].setVisible(i < health);
        }
    }

    @Override
    public void handleHeroDamage() {
        health--;
        updateHealthUI();

        if (health <= 0) {
            try {
                controller.gameOver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void triggerHeroBeepEffect() {
        Timeline flash = new Timeline(
                new KeyFrame(Duration.millis(100), event -> Hero.setOpacity(0)),
                new KeyFrame(Duration.millis(200), event -> Hero.setOpacity(1.0)));
        flash.setCycleCount(7);
        flash.play();
    }
}
