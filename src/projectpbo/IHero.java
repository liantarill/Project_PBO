/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import javafx.scene.input.KeyCode;

/**
 *
 * @author LENOVO
 */
public interface IHero {
    public void heroMovement(KeyCode code);

    public void updateHealthUI();

    public void handleHeroDamage();

}
