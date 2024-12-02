/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    @FXML
    private ImageView enemy1;
    @FXML
    private ImageView enemy2;
    @FXML
    private ImageView enemy3;
    @FXML
    private ImageView enemy4;
    @FXML
    private ImageView enemy5;
    @FXML
    private ImageView enemy6;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scene.setOnKeyPressed(this::Movement);
        
        scene.requestFocus();
        scene.setOnMouseClicked(event -> scene.requestFocus());
        
        System.out.println("Scene Width: " + scene.getWidth());
        System.out.println("Scene Height: " + scene.getHeight());
        
        createEnemyMovement(enemy1);
        createEnemyMovement(enemy2);
        createEnemyMovement(enemy3);
        createEnemyMovement(enemy4);
        createEnemyMovement(enemy5);
        createEnemyMovement(enemy6);
    }    
//contoh
    @FXML
    private void Movement(KeyEvent event) {
         switch(event.getCode()){
            case UP:
                if(Hero.getLayoutY() > 0){
                    Hero.setLayoutY(Hero.getLayoutY()-10);
                }
                break;
            case DOWN:
                if(Hero.getLayoutY() < 511){
                    Hero.setLayoutY(Hero.getLayoutY()+10);
                }
                break;
            case RIGHT:
                if(Hero.getLayoutX() < 700){
                    Hero.setLayoutX(Hero.getLayoutX()+10);
                }
                break;
            case LEFT:
                if(Hero.getLayoutX() > 0){
                    Hero.setLayoutX(Hero.getLayoutX()-10);
                }
                break;
        }
    }
    
    private void createEnemyMovement(ImageView enemy) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(enemy);
        transition.setByX(500);
        transition.setDuration(Duration.seconds(5));
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }
}
