package battleship.gui;

import battleship.game.*;
import io.cucumber.java.sl.In;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.LinkedList;

public class PlayerPane extends Pane implements PlayerObserver {

    public static final int OFFSET_BETWEEN_SEAS = 70;

    private Orientation currentOrientation = Orientation.N;
    private int shipPlacedCounter;
    private final int numRow;
    private final int numCol;
    private final int offsetX = GraphicalCell.SIZE / 2;
    private final int offsetY = GraphicalCell.SIZE / 2;
    private final int infoSize = 100;
    private final Player mainPlayer;
    private final Player otherPlayer;

    private VBox gameInfoBox;
    private VBox gameTimerBox;
    private ProgressBar mainPlayerProgressBar;

    private final File winMusic = new File("src/main/resources/sounds/win.mp3");
    private final Media wMusic = new Media(winMusic.toURI().toString());
    private final MediaPlayer winMediaPlayer = new MediaPlayer(wMusic);
    private final File shipMusic = new File("src/main/resources/sounds/place_ship.mp3");
    private final Media sMusic = new Media(shipMusic.toURI().toString());
    private final MediaPlayer shipMediaPlayer = new MediaPlayer(sMusic);
    private final File missMusic = new File("src/main/resources/sounds/miss.mp3");
    private final Media mMusic = new Media(missMusic.toURI().toString());
    private final MediaPlayer missMediaPlayer = new MediaPlayer(mMusic);
    private final File hitMusic = new File("src/main/resources/sounds/hit.mp3");
    private final Media hMusic = new Media(hitMusic.toURI().toString());
    private final MediaPlayer hitMediaPlayer = new MediaPlayer(hMusic);
    private final File bombMusic = new File("src/main/resources/sounds/bomb.mp3");
    private final Media bMusic = new Media(bombMusic.toURI().toString());
    private final MediaPlayer bombMediaPlayer = new MediaPlayer(bMusic);

    private final File loseMusic = new File("src/main/resources/sounds/lose.mp3");
    private final Media lMusic = new Media(loseMusic.toURI().toString());
    private final MediaPlayer loseMediaPlayer = new MediaPlayer(lMusic);


    private enum GameProgression {
        SHIP_PLACEMENT, PLAYING_GAME;

        private static GameProgression whichProgression(int nbrShipPlaced) {
            return (nbrShipPlaced < ShipType.values().length) ? SHIP_PLACEMENT : PLAYING_GAME;
        }
    }

    public PlayerPane(Player mainPlayer, Player otherPlayer, boolean isTimed) {
        this.mainPlayer = mainPlayer;
        this.otherPlayer = otherPlayer;
        numRow = mainPlayer.getBoard().getNumRow();
        numCol = mainPlayer.getBoard().getNumCol();
        boolean otherPlayerIsAi = otherPlayer instanceof AIPlayer;
        mainPlayer.getGameInfo().infoProperty().addListener((observable, oldValue, newValue) -> updateGameInfo(newValue));
        if (isTimed) {
            mainPlayer.timerProperty().addListener((observable, oldValue, newValue) -> updateTimer(newValue.intValue()));
        }

        setMinSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setMaxSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setPrefSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        loadBothSea(isTimed);


        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_1) {
            mainPlayer.setAmICurrentPlayer(true);
            otherPlayer.setAmICurrentPlayer(false); // TODO SHOULD BE MADE USELESS

            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).willPlayFirst());
            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).chooseShipsPlacements());
            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).chooseFirstShipPlacement(ShipType.values()[0]));
        }

        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_2) {
            mainPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).willPlayFirst());
        }


        this.setOnKeyPressed(event -> {
            if (mainPlayer.amICurrentPlayer()) {
                if (currentOrientation == null) {
                    currentOrientation = Orientation.N;
                }
                updateOrientation(event.getCode());
            }
        });

        setOnMouseClicked(event -> {
            if (mainPlayer.amICurrentPlayer()) {
                requestFocus();
                if ((event.getButton() == MouseButton.PRIMARY) && (event.getTarget() instanceof GraphicalCell gCell)) {
                    switch (GameProgression.whichProgression(shipPlacedCounter)) {
                        case SHIP_PLACEMENT:
                            if (gCell.isMine()) { // TODO condition about nonNull orientation

                                if (currentOrientation != null && mainPlayer.addShip(ShipType.values()[shipPlacedCounter], gCell.getCoordinates(), currentOrientation)) {
                                    shipMediaPlayer.setAutoPlay(true);
                                    shipMediaPlayer.play();

                                    ++shipPlacedCounter;
                                    if (shipPlacedCounter == ShipType.values().length) { // TODO REFACTOR AND PRETTIER
                                        if (otherPlayerIsAi) {
                                            ((AIPlayer) otherPlayer).addShips();
                                        } else {
                                            otherPlayer.setAmICurrentPlayer(true);
                                            mainPlayer.setAmICurrentPlayer(false);
                                        }
                                        mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).shipArePlaced());
                                        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_1) {
                                            otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).chooseShipsPlacements());
                                            otherPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).chooseFirstShipPlacement(ShipType.values()[0]));
                                        }

                                        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_2) {
                                            otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());
                                        }
                                    }
                                }
                            }
                            break;
                        case PLAYING_GAME:
                            mainPlayer.addObserver(this);
                            if (!gCell.isMine()) {
                                boolean hasBomb = mainPlayer.getHasBomb();
                                CellStatus newStatus = mainPlayer.handleShot(gCell.getCoordinates(), otherPlayer);
                                if (newStatus == CellStatus.HIT && hasBomb) {
                                    bombMediaPlayer.setAutoPlay(true);
                                    bombMediaPlayer.play();
                                } else if (newStatus == CellStatus.HIT || newStatus == CellStatus.ROCK_HIT) {
                                    hitMediaPlayer.setAutoPlay(true);
                                    hitMediaPlayer.play();
                                } else {
                                    missMediaPlayer.setAutoPlay(true);
                                    missMediaPlayer.play();
                                }

                                if (newStatus != CellStatus.ALREADY_HIT && newStatus != CellStatus.ALREADY_MISSED) {
                                    if (otherPlayer.getNumberOfRemainingShips() == 0) {
                                        winMediaPlayer.setAutoPlay(true);
                                        winMediaPlayer.play();

                                        mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).won());
                                        otherPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).won());

                                        if (isTimed) {
                                            mainPlayer.resetTimer();
                                            otherPlayer.resetTimer();
                                        }
                                        mainPlayer.setAmICurrentPlayer(false);
                                        otherPlayer.setAmICurrentPlayer(false);
                                    } else {
                                        if (otherPlayerIsAi) {
                                            ((AIPlayer) otherPlayer).handleShot(mainPlayer);
                                            if (mainPlayer.getNumberOfRemainingShips() == 0) {
                                                mainPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).won());
                                                loseMediaPlayer.setAutoPlay(true);
                                                loseMediaPlayer.play();
                                                mainPlayer.setAmICurrentPlayer(false);
                                            } else {
                                                mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).canPlay());
                                            }

                                        } else {
                                            if (isTimed) {
                                                mainPlayer.resetTimer();
                                                otherPlayer.startTimer();
                                            }

                                            mainPlayer.setAmICurrentPlayer(false);
                                            otherPlayer.setAmICurrentPlayer(true);
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
        });
        setOnMouseReleased(event -> {
            shipMediaPlayer.stop();
            winMediaPlayer.stop();
            missMediaPlayer.stop();
            hitMediaPlayer.stop();
        });
    }

    private void updateOrientation(KeyCode code) {
        switch (code) {
            case UP, W -> {
                currentOrientation = Orientation.N;
                System.out.println("n");
            }
            case DOWN, S -> {
                currentOrientation = Orientation.S;
                System.out.println("s");
            }
            case LEFT, A -> {
                currentOrientation = Orientation.W;
                System.out.println("w");
            }
            case RIGHT, D -> {
                currentOrientation = Orientation.E;
                System.out.println("e");
            }
            default -> {
                currentOrientation = Orientation.N;
            }
        }
    }

    private void loadBothSea(boolean isTimed) {

        Label mainPlayerlabel = new Label("Your Progress:");
        mainPlayerlabel.setFont(new Font(16));
        mainPlayerlabel.relocate(offsetX, offsetY + (numRow * GraphicalCell.SIZE) + 3 * ((double) OFFSET_BETWEEN_SEAS / 8) - 20);
        getChildren().add(mainPlayerlabel);

        mainPlayerProgressBar = new ProgressBar();
        mainPlayerProgressBar.setProgress(0.0);
        mainPlayerProgressBar.setPrefSize(numCol * GraphicalCell.SIZE - 9 * offsetX, (double) OFFSET_BETWEEN_SEAS / 4);
        mainPlayerProgressBar.relocate(offsetX, offsetY + (numRow * GraphicalCell.SIZE) + 3 * ((double) OFFSET_BETWEEN_SEAS / 8) + 10);
        mainPlayerProgressBar.setVisible(true);
        getChildren().add(mainPlayerProgressBar);

        if (isTimed) {
            gameTimerBox = new VBox();
            gameTimerBox.setStyle("-fx-background-color: gray;");
            gameTimerBox.setPrefSize((numCol * GraphicalCell.SIZE - 9 * offsetX) - 2, (double) OFFSET_BETWEEN_SEAS - 2);
            gameTimerBox.relocate(16 * offsetX, offsetY + (numRow * GraphicalCell.SIZE) + 3 * ((double) OFFSET_BETWEEN_SEAS / 8) - 23);
        }

        for (int row = 0; row < numRow; row++) {
            Text text = new Text(String.valueOf(row + 1));
            text.setFill(Color.BLUE);
            text.setFont(Font.font(12));
            text.relocate(((double) GraphicalCell.SIZE / 2) - text.getLayoutBounds().getWidth() - 3, ((row + 1) * GraphicalCell.SIZE) - (text.getLayoutBounds().getHeight() / 2));
            getChildren().add(text);

            for (int col = 0; col < numCol; col++) {
                if (row == 0) {
                    Text text2 = new Text(Character.valueOf((char) (col + 65)).toString());
                    text2.setFill(Color.BLUE);
                    text2.setFont(Font.font(12));
                    text2.relocate((col + 1) * GraphicalCell.SIZE, 0);
                    getChildren().add(text2);
                }

                GraphicalCell t = new GraphicalCell(otherPlayer.getBoard().getCell(new Coordinates(col, row)), false);
                t.relocate(offsetX + (GraphicalCell.SIZE * col), offsetY + (GraphicalCell.SIZE * row));
                getChildren().add(t);
            }
        }

        for (int row = 0; row < numRow; row++) {
            Text text = new Text(String.valueOf(row + 1));
            text.setFill(Color.BLUE);
            text.setFont(Font.font(12));
            text.relocate(((double) GraphicalCell.SIZE / 2) - text.getLayoutBounds().getWidth() - 3, ((row + 1) * GraphicalCell.SIZE) - (text.getLayoutBounds().getHeight() / 2) + GraphicalCell.SIZE * numRow + OFFSET_BETWEEN_SEAS);
            getChildren().add(text);

            for (int col = 0; col < numCol; col++) {
                if (row == 0) {
                    Text text2 = new Text(Character.valueOf((char) (col + 65)).toString());
                    text2.setFill(Color.BLUE);
                    text2.setFont(Font.font(12));
                    text2.relocate((col + 1) * GraphicalCell.SIZE, OFFSET_BETWEEN_SEAS + (GraphicalCell.SIZE * numRow));
                    getChildren().add(text2);
                }

                GraphicalCell t = new GraphicalCell(mainPlayer.getBoard().getCell(new Coordinates(col, row)), true);
                t.relocate(offsetX + (GraphicalCell.SIZE * col), offsetY + GraphicalCell.SIZE * numRow + OFFSET_BETWEEN_SEAS + (GraphicalCell.SIZE * row));
                getChildren().add(t);
            }
        }


        gameInfoBox = new VBox();
        gameInfoBox.setStyle("-fx-background-color: white;");
        gameInfoBox.setPrefSize(2 * offsetX + (GraphicalCell.SIZE * numCol), infoSize);
        gameInfoBox.relocate(0, 2 * GraphicalCell.SIZE * numRow + 2 * offsetY + OFFSET_BETWEEN_SEAS);

        ScrollPane scrollPane = new ScrollPane(gameInfoBox);
        scrollPane.setPrefSize(2 * offsetX + (GraphicalCell.SIZE * numCol), infoSize);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.relocate(0, 2 * GraphicalCell.SIZE * numRow + 2 * offsetY + OFFSET_BETWEEN_SEAS);
        getChildren().add(scrollPane);

        getChildren().add(gameInfoBox);
        if (isTimed) {
            getChildren().add(gameTimerBox);
        }
    }


    private void updateGameInfo(LinkedList<String> infos) {
        Label titleLabel = new Label("Game Info:");
        titleLabel.setFont(new Font(18));
        gameInfoBox.getChildren().clear();
        gameInfoBox.getChildren().add(titleLabel);

        for (int i = infos.size() - 1; i >= 0; i--) {
            Label gameInfoLabel = new Label("- " + infos.get(i));
            gameInfoLabel.setFont(new Font(12));
            gameInfoLabel.setMaxWidth(2 * offsetX + (GraphicalCell.SIZE * numCol));
            gameInfoLabel.setPrefWidth(2 * offsetX + (GraphicalCell.SIZE * numCol));
            gameInfoLabel.setWrapText(true);
            gameInfoBox.getChildren().add(gameInfoLabel);
        }
    }

    private void updateTimer(int newTimerValue) {
        Label titleLabel = new Label("Timer:");
        titleLabel.setFont(new Font(28));
        gameTimerBox.getChildren().clear();
        gameTimerBox.getChildren().add(titleLabel);

        Label timerLabel1 = new Label(String.valueOf(newTimerValue));
        timerLabel1.setFont(Font.font(18));
        timerLabel1.setMaxWidth(2 * offsetX + (GraphicalCell.SIZE * numCol));
        timerLabel1.setPrefWidth(2 * offsetX + (GraphicalCell.SIZE * numCol));
        timerLabel1.setWrapText(true);
        gameTimerBox.getChildren().add(timerLabel1);


        if (newTimerValue <= 0) {
            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).time_is_up());
            otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());
            mainPlayer.resetTimer();
            mainPlayer.setAmICurrentPlayer(false);

            otherPlayer.startTimer();
            otherPlayer.setAmICurrentPlayer(true);
        }

    }

    @Override
    public void onRemainingShipsChanged(int remainingShips) {
        System.out.println(mainPlayer.getPlayerId());
        mainPlayerProgressBar.setProgress(1 - remainingShips / (double) ShipType.values().length);
    }
}