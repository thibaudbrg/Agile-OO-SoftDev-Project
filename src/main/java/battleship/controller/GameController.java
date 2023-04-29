package battleship.controller;

import battleship.game.*;
import battleship.gui.*;
import javafx.scene.input.KeyCode;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class GameController {
    private final Game game;
    private final GraphicalGame graphicalGame;
    private final boolean isTimed;


    private Orientation currentOrientation = Orientation.N;
    private int shipPlacedCounter;




    public GameController(GameMode gameMode, int numCol, int numRow, boolean isTimed) {
        this.isTimed = isTimed;

        game = Game.getInstance(gameMode, numCol, numRow);
        graphicalGame = GraphicalGame.getInstance(gameMode, game.getPlayers(), isTimed);

        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        player1.getGameInfo().infoProperty().addListener((observable, oldValue, newValue) -> graphicalGame.getPlayerPane1().updateGameInfo(newValue));

        player1.getGameInfo().addInfo(new Info(PlayerId.PLAYER_1).willPlayFirst());
        player1.getGameInfo().addInfo(new Info(player1.getPlayerId()).chooseShipsPlacements());
        player1.getGameInfo().addInfo(new Info(player1.getPlayerId()).chooseFirstShipPlacement(ShipType.values()[0]));


        if (gameMode == GameMode.MULTIPLAYER) {
            player2.getGameInfo().infoProperty().addListener((observable, oldValue, newValue) -> graphicalGame.getPlayerPane2().updateGameInfo(newValue));
            player2.getGameInfo().addInfo(new Info(PlayerId.PLAYER_1).willPlayFirst());

            graphicalGame.getPlayerPane2().setOnMouseClicked(this::mouseClicked);
            graphicalGame.getPlayerPane2().setOnKeyPressed(this::keyPressed);
            graphicalGame.getPlayerPane2().setOnMouseReleased(this::mouseReleased);

            if (isTimed) {
                setupTimerListener(player1, graphicalGame.getPlayerPane1(), player2);
                setupTimerListener(player2, graphicalGame.getPlayerPane2(), player1);
            }
        }

        graphicalGame.getPlayerPane1().setOnMouseClicked(this::mouseClicked);
        graphicalGame.getPlayerPane1().setOnKeyPressed(this::keyPressed);
        graphicalGame.getPlayerPane1().setOnMouseReleased(this::mouseReleased);
    }


    private void mouseReleased(MouseEvent mouseEvent) {
        SoundManager.stop(SoundEffect.SHIP);
        SoundManager.stop(SoundEffect.WIN);
        SoundManager.stop(SoundEffect.MISS);
        SoundManager.stop(SoundEffect.HIT);
    }

    private void keyPressed(KeyEvent keyEvent) {
        if (currentOrientation == null) {
            currentOrientation = Orientation.N;
        }
        updateOrientation(keyEvent.getCode());
    }


    private void mouseClicked(MouseEvent mouseEvent) {
        PlayerPane sourcePane = (PlayerPane) mouseEvent.getSource();
        Player mainPlayer = sourcePane.getPlayer();
        Player otherPlayer = (game.getPlayers().get(0) == mainPlayer) ? game.getPlayers().get(1) : game.getPlayers().get(0);
        boolean otherPlayerIsAi = otherPlayer instanceof AIPlayer;


        if (mainPlayer == game.getCurrentPlayer()) {
            sourcePane.requestFocus();
            if ((mouseEvent.getButton() == MouseButton.PRIMARY) && (mouseEvent.getTarget() instanceof GraphicalCell gCell)) {

                switch (GameProgression.whichProgression(shipPlacedCounter)) {
                    case SHIP_PLACEMENT_PLAYER_1:
                        if (gCell.isMine()) {
                            if (currentOrientation != null && mainPlayer.addShip(ShipType.values()[shipPlacedCounter], gCell.getCoordinates(), currentOrientation)) {
                                SoundManager.play(SoundEffect.SHIP);

                                ++shipPlacedCounter;

                                if (GameProgression.allShipsPlacedForPlayer(shipPlacedCounter, 1)) {

                                    mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).shipArePlaced());
                                    otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).chooseShipsPlacements());
                                    otherPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).chooseFirstShipPlacement(ShipType.values()[0]));
                                    otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());

                                    if (otherPlayerIsAi) {
                                        ((AIPlayer) otherPlayer).addShips();
                                        shipPlacedCounter = 2 * ShipType.values().length;
                                        mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).canPlay());
                                    } else {
                                        game.switchPlayer();
                                    }
                                }
                            }
                        }
                        break;

                    case SHIP_PLACEMENT_PLAYER_2:
                        if (gCell.isMine()) {
                            if (currentOrientation != null && game.getCurrentPlayer().addShip(ShipType.values()[shipPlacedCounter % 5], gCell.getCoordinates(), currentOrientation)) {
                                SoundManager.play(SoundEffect.SHIP);
                                ++shipPlacedCounter;
                            }
                        }

                        if (GameProgression.allShipsPlacedForPlayer(shipPlacedCounter, 2)) {
                            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).shipArePlaced());
                            otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());

                            game.switchPlayer();
                        }
                        break;


                    case PLAYING_GAME:
                        if (!gCell.isMine()) {
                            boolean hasBomb = mainPlayer.getHasBomb();
                            CellStatus newStatus = mainPlayer.handleShot(gCell.getCoordinates(), otherPlayer);

                            if (newStatus == CellStatus.HIT && hasBomb) {
                                SoundManager.play(SoundEffect.BOMB);
                            } else if (newStatus == CellStatus.HIT || newStatus == CellStatus.ROCK_HIT) {
                                SoundManager.play(SoundEffect.HIT);
                            } else {
                                SoundManager.play(SoundEffect.MISS);
                            }

                            if (newStatus != CellStatus.ALREADY_HIT && newStatus != CellStatus.ALREADY_MISSED) {
                                if (otherPlayer.getNumberOfRemainingShips() == 0) {
                                    SoundManager.play(SoundEffect.WIN);

                                    mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).won());
                                    otherPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).won());

                                    if (isTimed) {
                                        mainPlayer.resetTimer();
                                        otherPlayer.resetTimer();
                                    }
                                    game.gameEnded();

                                } else if (otherPlayerIsAi) {
                                    Coordinates aiCoords = ((AIPlayer) otherPlayer).getAICoordinates();
                                    otherPlayer.handleShot(aiCoords, mainPlayer);
                                    if (mainPlayer.getNumberOfRemainingShips() == 0) {
                                        mainPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).won());
                                        SoundManager.play(SoundEffect.LOSE);

                                        game.gameEnded();
                                    } else {
                                        mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).canPlay());
                                    }

                                } else {
                                    if (isTimed) {
                                        mainPlayer.resetTimer();
                                        otherPlayer.startTimer();
                                    }

                                    game.switchPlayer();
                                }
                                otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());
                            }
                        } else {
                            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).canPlay());
                        }
                }
            }

        }
    }


    private void setupTimerListener(Player currentPlayer, PlayerPane currentPane, Player otherPlayer) {
        currentPlayer.timerProperty().addListener((observable, oldValue, newValue) -> {
            currentPane.updateTimer(newValue.intValue());

            if (newValue.intValue() <= 0) {
                currentPlayer.getGameInfo().addInfo(new Info(currentPlayer.getPlayerId()).time_is_up());
                otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());

                currentPlayer.resetTimer();
                otherPlayer.startTimer();
                game.switchPlayer();
            }
        });
    }


    private void updateOrientation(KeyCode code) {
        switch (code) {
            case UP, W -> currentOrientation = Orientation.N;
            case DOWN, S -> currentOrientation = Orientation.S;
            case LEFT, A -> currentOrientation = Orientation.W;
            case RIGHT, D -> currentOrientation = Orientation.E;
            default -> currentOrientation = Orientation.N;
        }
    }


}
