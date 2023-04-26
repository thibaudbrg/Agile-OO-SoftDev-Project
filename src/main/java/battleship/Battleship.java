package battleship;

import battleship.controller.GameController;
import battleship.controller.GameCreationListener;
import battleship.gui.GameMode;
import battleship.gui.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.io.File;

public class Battleship extends Application implements GameCreationListener {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            String path = "src/main/resources/sounds/gamemusic.wav";
            Media media =  new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            SceneManager manager = new SceneManager(this);
            mediaPlayer.setAutoPlay(true);
            primaryStage.setTitle("BattleShip");
            primaryStage.setScene(manager.getMainStage().getScene());
            primaryStage.setResizable(false);
            mediaPlayer.play();
            primaryStage.setOnCloseRequest(x -> {
                x.consume();
                if (confirm()) {
                    Platform.exit();
                }
            });
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }



    public static boolean confirm() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Ship commander !!");
        alert.setTitle("Not enjoying the game?");
        alert.setContentText("Are you sure you want to quit?");
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && (result.get() == ButtonType.OK);
    }

    @Override
    public void onGameCreate(GameMode gameMode, int numRows, int numCols, boolean isTimed) {
        GameController gameController = new GameController(gameMode, numRows, numCols, isTimed);
    }
}

