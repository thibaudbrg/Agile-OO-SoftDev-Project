package battleship.gui;

import battleship.game.*;
import battleship.gui.widgets.GraphicalCell;
import battleship.gui.widgets.PlayerPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class GraphicalGame implements Game.TurnFinishedListener {

    private final Player player1;
    private final Player player2;
    private PlayerPane playerPane1;
    private PlayerPane playerPane2;

    private boolean theOtherPlayerAlreadyWon = false;

    private final Orientation currentOrientation = Orientation.N;

    private int shipPlacedCounter;


    private enum GameProgression {
        SHIP_PLACEMENT, PLAYING_GAME;

        private static GameProgression whichProgression(int nbrShipPlaced) {
            return (nbrShipPlaced < ShipType.values().length) ? SHIP_PLACEMENT : PLAYING_GAME;
        }
    }

    public GraphicalGame(GameMode gameMode, List<Player> players) {
        this.player1 = players.get(0);
        this.player2 = players.get(1);

        switch (gameMode) {
            case MULTIPLAYER -> {
                Stage stage1 = new Stage();
                Stage stage2 = new Stage();

                this.playerPane1 = new PlayerPane(player1, player2);
                this.playerPane2 = new PlayerPane(player2, player1);

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
                PlayerPane playerPane = new PlayerPane(player1, player2);
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


    @Override
    public void onTurnFinished() {
        // Release the semaphore to inform the Game class that the turn is finished
        Game.getTurnSemaphore().release();
    }

    public void setPlayerPaneClickable(Player currentPlayer, Player otherPlayer) {
        PlayerPane currentPlayerPane = (currentPlayer.getPlayerId() == PlayerId.PLAYER_1) ? playerPane1 : playerPane2;

        currentPlayerPane.setOnMouseClicked(event -> {
            if (event.getTarget() instanceof GraphicalCell gCell && (event.getButton() == MouseButton.PRIMARY)) {
                System.out.println("Just Clicked");
                switch (GameProgression.whichProgression(shipPlacedCounter)) {
                    case SHIP_PLACEMENT:
                        if (gCell.isMine()) { // TODO condition about nonNull orientation

                            if (currentOrientation != null && currentPlayer.addShip(ShipType.values()[shipPlacedCounter], gCell.getCoordinates(), currentOrientation)) {
                                ////shipMediaPlayer.setAutoPlay(true);//////////////////////////////
                                ////shipMediaPlayer.play();//////////////////////////////
                                System.out.println("Good Placement!");
                                ++shipPlacedCounter;

                                if (shipPlacedCounter == ShipType.values().length) { // TODO REFACTOR AND PRETTIER
                                    //otherPlayer.setAmICurrentPlayer(true);
                                    //mainPlayer.setAmICurrentPlayer(false);
                                    currentPlayerPane.setProgressBarsVisible(true);

                                }

                                // Turn finished, notify the listener
                                onTurnFinished();

                            } else {
                                System.out.println("Placement Failed!");
                            }
                        }
                        break;
                    case PLAYING_GAME:

                        //mainPlayerProgressBar.setVisible(true);
                        //otherPlayerProgressBar.setVisible(true);
                        Alert alert = null;
                        ButtonType buttonType = null;
                        Optional<ButtonType> result = null;
                        if (currentPlayer.getNumberOfRemainingShips() == 0) {
                            theOtherPlayerAlreadyWon = true;
                            //winMediaPlayer.setAutoPlay(true);//////////////////////////////
                            //winMediaPlayer.play();//////////////////////////////
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("The other player: " + otherPlayer + " Wins!!");
                            //System.out.println(mainPlayer.getPlayerId() + " Wins!!");
                            alert.setContentText("The game is over. Click on the button to move back to the game menu.");
                            buttonType = new ButtonType("OK");
                            alert.getButtonTypes().setAll(buttonType);
                            result = alert.showAndWait();
                            if (result.get() == buttonType) {
                                Stage stage = (Stage) currentPlayerPane.getScene().getWindow();
                                stage.close();
                                Main.main(new String[]{});
                            }
                        }
                        if (!gCell.isMine()) {
                            currentPlayer.handleShot(gCell.getCoordinates(), otherPlayer);
                            if (otherPlayer.getNumberOfRemainingShips() == 0 && !theOtherPlayerAlreadyWon) {
                                //winMediaPlayer.setAutoPlay(true);//////////////////////////////
                                //winMediaPlayer.play();//////////////////////////////
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("You, " + currentPlayer.getPlayerId() + " Win!!");
                                //System.out.println(mainPlayer.getPlayerId() + " Wins!!");
                                alert.setContentText("The game is over. Click on the button to move back to the game menu.");
                                buttonType = new ButtonType("OK");
                                alert.getButtonTypes().setAll(buttonType);
                                result = alert.showAndWait();
                                if (result.get() == buttonType) {
                                    Stage stage = (Stage) currentPlayerPane.getScene().getWindow();
                                    stage.close();
                                    Main.main(new String[]{});

                                }
                                //otherPlayer.setAmICurrentPlayer(false);
                                //mainPlayer.setAmICurrentPlayer(false);
                            }
                            currentPlayerPane.setProgressBarsProgress(
                                    (double) currentPlayer.getNumberOfRemainingShips() / ShipType.values().length,
                                    (double) otherPlayer.getNumberOfRemainingShips() / ShipType.values().length);
                            //otherPlayer.setAmICurrentPlayer(true);
                            //mainPlayer.setAmICurrentPlayer(false);
                        }


                }
            }
        });
    }

    public void setPlayerPaneNonClickable(Player otherPlayer, Player currentPlayer) {
        PlayerPane otherPlayerPane = (otherPlayer.getPlayerId() == PlayerId.PLAYER_1) ? playerPane1 : playerPane2;

        otherPlayerPane.setOnMouseClicked(event -> {
            if (event.getTarget() instanceof GraphicalCell gCell && (event.getButton() == MouseButton.PRIMARY)) {
                event.consume(); // Consume the event to disable clicking
            }
        });
    }

}
