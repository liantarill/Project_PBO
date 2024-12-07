/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package projectpbo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author LENOVO
 */
public class musicPlayer {

    private MediaPlayer mediaPlayer;

    public void startMusic() {
        String musicFile = getClass().getResource("music/startMenu.mp3").toExternalForm();

        Media backgroundMusic = new Media(musicFile);
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play();
    }

    public void inGameMusic() {
        String musicFile = getClass().getResource("music/inGame.mp3").toExternalForm();

        Media backgroundMusic = new Media(musicFile);
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play();
    }

    public void shotSFX() {
        String musicFile = getClass().getResource("music/shot.mp3").toExternalForm();

        Media backgroundMusic = new Media(musicFile);
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.play();
    }

    public void explosionSFX() {
        String musicFile = getClass().getResource("music/explosion.mp3").toExternalForm();

        Media backgroundMusic = new Media(musicFile);
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.play();
    }

    public void dead() {
        String musicFile = getClass().getResource("music/dead.mp3").toExternalForm();

        Media backgroundMusic = new Media(musicFile);
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.play();
    }

    public void gameOverMusic() {
        String musicFile = getClass().getResource("music/gameOver.mp3").toExternalForm();

        Media backgroundMusic = new Media(musicFile);
        mediaPlayer = new MediaPlayer(backgroundMusic);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play();
    }

    public void stopMusic() {
        mediaPlayer.stop();
    }

}