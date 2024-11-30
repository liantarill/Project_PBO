/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author LENOVO
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane scene;
    @FXML
    private ImageView Hero;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        scene.setOnKeyPressed(this::Movement);
        
        scene.requestFocus();
        scene.setOnMouseClicked(event -> scene.requestFocus());
    }    

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
    
}
