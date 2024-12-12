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
public class Hero extends Character implements IHero {
    private AnchorPane scene;
    public ImageView Hero;
    private double heroX;
    private double heroY;
    private MainPageController controller;
    private int health;

    public Hero(AnchorPane scene, ImageView Hero, MainPageController controller) {
        super(scene, Hero, controller);
        health = 5;

        System.out.println("Hero initialized: " + Hero);
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void heroMovement() {

        if (getController().getPressedKeys().contains(KeyCode.W)) {
            if (getCharacterY() > 0) {
                setCharacterY(getCharacterY() - 10);
            }
        }
        if (getController().getPressedKeys().contains(KeyCode.S)) {
            if (getCharacterY() < getScene().getHeight() - getCharacterImage().getFitHeight()) {
                setCharacterY(getCharacterY() + 10);
            }
        }
        if (getController().getPressedKeys().contains(KeyCode.D)) {
            if (getCharacterX() < getScene().getWidth() - getCharacterImage().getFitWidth()) {
                getCharacterImage().setLayoutX(getCharacterX() + 10);
            }
        }
        if (getController().getPressedKeys().contains(KeyCode.A)) {
            if (getCharacterX() > 0) {
                setCharacterX(getCharacterX() - 10);
            }
        }
        if (getController().getPressedKeys().contains(KeyCode.SPACE)) {
            getController().bullet.fireBulletWithDelay();
        }

    }

    @Override
    public void updateHealthUI() {
        ImageView[] healthBars = {
                getController().health1,
                getController().health2,
                getController().health3,
                getController().health4,
                getController().health5 };
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
                getController().gameOver();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void triggerHeroBeepEffect() {
        Timeline flash = new Timeline(
                new KeyFrame(Duration.millis(100), event -> characterImage.setOpacity(0)),
                new KeyFrame(Duration.millis(200), event -> characterImage.setOpacity(1.0)));
        flash.setCycleCount(7);
        flash.play();
    }
}
