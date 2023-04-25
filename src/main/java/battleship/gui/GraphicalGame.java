package battleship.gui;

import battleship.game.Player;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class GraphicalGame {

    private GraphicalGame() {
    }

    public static void initial(GameMode gameMode, List<Player> players,boolean isTimed) {
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        switch (gameMode) {
            case MULTIPLAYER -> {
                Stage stage1 = new Stage();
                Stage stage2 = new Stage();
                PlayerPane playerPane1 = new PlayerPane(player1, player2,isTimed);
                PlayerPane playerPane2 = new PlayerPane(player2, player1,isTimed);
                BorderPane container1 = new BorderPane();
                BorderPane container2 = new BorderPane();

                container1.setCenter(playerPane1);
                container2.setCenter(playerPane2);

                Scene scene1 = new Scene(container1);
                Scene scene2 = new Scene(container2);


                stage1.setScene(scene1);
                stage1.setX(300);
                stage1.setY(0);

                stage2.setScene(scene2);
                stage2.setX(800);
                stage2.setY(0);

                stage1.setTitle("Battleship - " + gameMode.getExtendedName() + " - Player 1");
                stage2.setTitle("Battleship - " + gameMode.getExtendedName() + " - Player 2");

                stage1.show();
                stage2.show();
            }
            case EASY, HARD -> {
                Stage stage = new Stage();
                PlayerPane playerPane = new PlayerPane(player1, player2,false);
                BorderPane container = new BorderPane();
                container.setCenter(playerPane);

                Scene scene = new Scene(container);
                stage.setScene(scene);
                stage.setX(500);
                stage.setY(0);
                stage.setTitle("Battleship - " + gameMode.getExtendedName());
                stage.show();
            }
        }
    }
}
