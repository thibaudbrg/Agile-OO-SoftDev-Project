package battleship.gui;

import battleship.gui.widgets.Sea;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GraphicalGame {

    GraphicalGame(GameMode gameMode) {
        switch (gameMode) {
            case MULTIPLAYER:

                Stage stage1 = new Stage();
                Stage stage2 = new Stage();

                Sea sea1 = new Sea();
                Sea sea2 = new Sea();


                BorderPane container1 = new BorderPane();
                BorderPane container2 = new BorderPane();

                container1.setCenter(sea1);
                container2.setCenter(sea2);

                Scene scene1 = new Scene(container1);
                Scene scene2 = new Scene(container2);

                stage1.setScene(scene1);
                stage2.setScene(scene2);

                stage1.setTitle("Battleship - " + gameMode.getExtendedName() + " Player 1");
                stage2.setTitle("Battleship - " + gameMode.getExtendedName() + " Player 2");

                stage1.show();
                stage2.show();
                break;

            case EASY, HARD:
                Stage stage = new Stage();
                Sea sea = new Sea();
                BorderPane container = new BorderPane();
                container.setCenter(sea);
                Scene scene = new Scene(container);
                stage.setScene(scene);
                stage.setTitle("Battleship - " + gameMode.getExtendedName());
                stage.show();
                break;
        }
    }
}
