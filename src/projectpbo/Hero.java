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
    AnchorPane scene;
    ImageView Hero;

    MainPageController controller;

    public int health = 5;

    public Hero(AnchorPane scene, ImageView Hero, MainPageController controller) {
        this.scene = scene;
        this.Hero = Hero;
        this.controller = controller;
    }

    public ImageView getHero() {
        return Hero;
    }

    public void setHero(ImageView hero) {
        Hero = hero;
    }

    @Override
    public void heroMovement(KeyCode code) {
        switch (code) {
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
                controller.bullet.fireBulletWithDelay();
                break;
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
