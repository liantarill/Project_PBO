/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author LENOVO
 */
public class Character {
    protected AnchorPane scene;

    protected ImageView characterImage;

    protected MainPageController controller;

    protected double characterImageX;
    protected double characterImageY;

    public Character(AnchorPane scene, ImageView characterImage, MainPageController controller) {
        this.scene = scene;
        this.characterImage = characterImage;
        this.controller = controller;
    }

    public AnchorPane getScene() {
        return scene;
    }

    public void setScene(AnchorPane scene) {
        this.scene = scene;
    }

    public ImageView getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(ImageView characterImage) {
        this.characterImage = characterImage;
    }

    public MainPageController getController() {
        return controller;
    }

    public void setController(MainPageController controller) {
        this.controller = controller;
    }

    public double getCharacterX() {
        return characterImage.getLayoutX();
    }

    public void setCharacterX(double X) {
        characterImage.setLayoutX(X);
        characterImageX = characterImage.getLayoutX();
    }

    public double getCharacterY() {
        return characterImage.getLayoutY();
    }

    public void setCharacterY(double Y) {
        characterImage.setLayoutY(Y);
        characterImageY = characterImage.getLayoutY();
    }
}
