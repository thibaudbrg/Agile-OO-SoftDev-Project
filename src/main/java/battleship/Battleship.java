package battleship;
import battleship.gui.ConfirmEndGame;
import battleship.gui.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class Battleship extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            String path = "src/main/ressources/sounds/gamemusic.wav";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            SceneManager manager = new SceneManager();
            mediaPlayer.setAutoPlay(true);
            primaryStage.setTitle("BattleShip");
            primaryStage.setScene(manager.getMainStage().getScene());
            primaryStage.setResizable(false);
            mediaPlayer.play();
            primaryStage.setOnCloseRequest(x -> {
                x.consume();
                if (ConfirmEndGame.confirm()) {
                    Platform.exit();
                }
            });
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}