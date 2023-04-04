package battleship.gui;

import battleship.game.Player;
import battleship.gui.widgets.PlayerPane;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class GraphicalGame {

    private GraphicalGame() {
    }

    public static void initial(GameMode gameMode, List<Player> players) {
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        switch (gameMode) {
            case MULTIPLAYER -> {
                Stage stage1 = new Stage();
                Stage stage2 = new Stage();

                PlayerPane playerPane1 = new PlayerPane(player1, player2);
                PlayerPane playerPane2 = new PlayerPane(player2, player1);

                BorderPane container1 = new BorderPane();
                BorderPane container2 = new BorderPane();

                container1.setCenter(playerPane1);
                container2.setCenter(playerPane2);

                Scene scene1 = new Scene(container1);
                Scene scene2 = new Scene(container2);

                stage1.setScene(scene1);
                stage2.setScene(scene2);

                stage1.setTitle("Battleship - " + gameMode.getExtendedName() + " Player 1");
                stage2.setTitle("Battleship - " + gameMode.getExtendedName() + " Player 2");

                stage1.show();
                stage2.show();
            }
            case EASY, HARD -> {
                Stage stage = new Stage();
                PlayerPane playerPane = new PlayerPane(player1, player2);
                BorderPane container = new BorderPane();
                container.setCenter(playerPane);

                Scene scene = new Scene(container);
                stage.setScene(scene);
                stage.setTitle("Battleship - " + gameMode.getExtendedName());
                stage.show();
            }
        }
    }
}
