package battleship;

import battleship.gui.ConfirmEndGame;
import battleship.gui.SceneManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Battleship extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            SceneManager manager = new SceneManager();
            //primaryStage = manager.getMainStage();
            primaryStage.setTitle("BattleShip");
            primaryStage.setScene(manager.getMainStage().getScene());
            primaryStage.setResizable(false);
            //primaryStage.setFullScreen(true);
            primaryStage.setOnCloseRequest(x -> {
                x.consume();
                if (ConfirmEndGame.confirm()) {
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
}