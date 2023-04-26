package battleship.controller;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {

    private static final File winMusic = new File("src/main/resources/sounds/win.mp3");
    private static final Media wMusic = new Media(winMusic.toURI().toString());
    private static final MediaPlayer winMediaPlayer = new MediaPlayer(wMusic);

    private static final File shipMusic = new File("src/main/resources/sounds/place_ship.mp3");
    private static final Media sMusic = new Media(shipMusic.toURI().toString());
    private static final MediaPlayer shipMediaPlayer = new MediaPlayer(sMusic);

    private static final File missMusic = new File("src/main/resources/sounds/miss.mp3");
    private static final Media mMusic = new Media(missMusic.toURI().toString());
    private static final MediaPlayer missMediaPlayer = new MediaPlayer(mMusic);

    private static final File hitMusic = new File("src/main/resources/sounds/hit.mp3");
    private static final Media hMusic = new Media(hitMusic.toURI().toString());
    private static final MediaPlayer hitMediaPlayer = new MediaPlayer(hMusic);

    private static final File bombMusic = new File("src/main/resources/sounds/bomb.mp3");
    private static final Media bMusic = new Media(bombMusic.toURI().toString());
    private static final MediaPlayer bombMediaPlayer = new MediaPlayer(bMusic);

    private static final File loseMusic = new File("src/main/resources/sounds/lose.mp3");
    private static final Media lMusic = new Media(loseMusic.toURI().toString());
    private static final MediaPlayer loseMediaPlayer = new MediaPlayer(lMusic);


    public enum SoundEffect {
        WIN, SHIP, MISS, HIT, BOMB, LOSE
    }

    public static void play(SoundEffect effect) {
        switch (effect) {
            case WIN -> {
                winMediaPlayer.setAutoPlay(true);
                winMediaPlayer.play();
            }
            case SHIP -> {
                shipMediaPlayer.setAutoPlay(true);
                shipMediaPlayer.play();
            }
            case MISS -> {
                missMediaPlayer.setAutoPlay(true);
                missMediaPlayer.play();
            }
            case HIT -> {
                hitMediaPlayer.setAutoPlay(true);
                hitMediaPlayer.play();
            }
            case BOMB -> {
                bombMediaPlayer.setAutoPlay(true);
                bombMediaPlayer.play();
            }
            case LOSE -> {
                loseMediaPlayer.setAutoPlay(true);
                loseMediaPlayer.play();
            }
        }
    }

    public static void stop(SoundEffect effect) {
        switch (effect) {
            case WIN -> winMediaPlayer.stop();
            case SHIP -> shipMediaPlayer.stop();
            case MISS -> missMediaPlayer.stop();
            case HIT -> hitMediaPlayer.stop();
            case BOMB -> bombMediaPlayer.stop();
            case LOSE -> loseMediaPlayer.stop();
        }
    }
}