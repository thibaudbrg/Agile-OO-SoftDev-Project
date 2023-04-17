package battleship.gui;

import battleship.game.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;


public class PlayerPane extends Pane {

    private final int numRow;
    private final int numCol;
    public static final int OFFSET_BETWEEN_SEAS = 70;
    private final int offsetX = GraphicalCell.SIZE / 2;
    private final int offsetY = GraphicalCell.SIZE / 2;

    private final int infoSize = 100;

    private Orientation currentOrientation = Orientation.N;

    private int shipPlacedCounter;

    private final Player mainPlayer;
    private final Player otherPlayer;
    VBox gameInfoBox;

    ProgressBar mainPlayerProgressBar;
    ProgressBar otherPlayerProgressBar;

    ////File winMusic = new File("battleship/gui/sounds/win.mp3");//////////////////////////////
    ////Media wMusic = new Media(winMusic.toURI().toString());//////////////////////////////
    ////MediaPlayer winMediaPlayer = new MediaPlayer(wMusic);//////////////////////////////

    ////File shipMusic = new File("battleship/gui/sounds/place_ship.mp3");//////////////////////////////
    ////Media sMusic = new Media(shipMusic.toURI().toString());//////////////////////////////
    ////MediaPlayer shipMediaPlayer = new MediaPlayer(sMusic);//////////////////////////////

    private enum GameProgression {
        SHIP_PLACEMENT, PLAYING_GAME;

        private static GameProgression whichProgression(int nbrShipPlaced) {
            return (nbrShipPlaced < ShipType.values().length) ? SHIP_PLACEMENT : PLAYING_GAME;
        }
    }

    public PlayerPane(Player mainPlayer, Player otherPlayer) {

        this.mainPlayer = mainPlayer;
        this.otherPlayer = otherPlayer;
        numRow = mainPlayer.getBoard().getNumRow();
        numCol = mainPlayer.getBoard().getNumCol();
        mainPlayer.getGameInfo().infoProperty().addListener((observable, oldValue, newValue) -> updateGameInfo(newValue));

        setMinSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setMaxSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setPrefSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        loadBothSea();
        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_1) {
            mainPlayer.setAmICurrentPlayer(true);
            otherPlayer.setAmICurrentPlayer(false); // TODO SHOULD BE MADE USELESS

            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).willPlayFirst());
            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).chooseShipsPlacements());
            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).chooseFirstShipPlacement(ShipType.values()[0]));
        }

        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_2) {
            mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).willPlayFirst());
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
                                    ////shipMediaPlayer.setAutoPlay(true);//////////////////////////////
                                    ////shipMediaPlayer.play();//////////////////////////////

                                    ++shipPlacedCounter;
                                    if (shipPlacedCounter == ShipType.values().length) { // TODO REFACTOR AND PRETTIER
                                        otherPlayer.setAmICurrentPlayer(true);
                                        mainPlayer.setAmICurrentPlayer(false);

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
                            mainPlayerProgressBar.setVisible(true);
                            otherPlayerProgressBar.setVisible(true);

                            if (!gCell.isMine()) {
                                mainPlayer.handleShot(gCell.getCoordinates(), otherPlayer);
                                mainPlayerProgressBar.setProgress((double) mainPlayer.getNumberOfRemainingShips() / ShipType.values().length);
                                otherPlayerProgressBar.setProgress((double) otherPlayer.getNumberOfRemainingShips() / ShipType.values().length);
                                if (otherPlayer.getNumberOfRemainingShips() == 0) {
                                    //winMediaPlayer.setAutoPlay(true);//////////////////////////////
                                    //winMediaPlayer.play();//////////////////////////////

                                    mainPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).won());
                                    otherPlayer.getGameInfo().addInfo(new Info(mainPlayer.getPlayerId()).won());

                                    otherPlayer.setAmICurrentPlayer(false);
                                    mainPlayer.setAmICurrentPlayer(false);
                                }
                                else {
                                    otherPlayer.setAmICurrentPlayer(true);
                                    mainPlayer.setAmICurrentPlayer(false);
                                    otherPlayer.getGameInfo().addInfo(new Info(otherPlayer.getPlayerId()).canPlay());
                                }

                            }


                    }
                }
            }
        });
    }

    private void updateOrientation(KeyCode code) {
        switch (code) {
            case A -> {
                currentOrientation = Orientation.N;
                System.out.println("n");
            }
            case DOWN -> {
                currentOrientation = Orientation.S;
                System.out.println("s");
            }
            case LEFT -> {
                currentOrientation = Orientation.W;
                System.out.println("w");
            }
            case RIGHT -> {
                currentOrientation = Orientation.E;
                System.out.println("e");
            }
            default -> {
            }
        }
    }

    private void loadBothSea() {

        Label mainPlayerlabel = new Label("Up:");
        mainPlayerlabel.setFont(new Font(16));
        mainPlayerlabel.relocate(22, offsetY - 30 + (numRow * GraphicalCell.SIZE) + ((double) OFFSET_BETWEEN_SEAS / 2));
        getChildren().add(mainPlayerlabel);

        Label otherPlayerlabel = new Label("Down:");
        otherPlayerlabel.setFont(new Font(16));
        otherPlayerlabel.relocate(22, offsetY + (numRow * GraphicalCell.SIZE) + ((double) OFFSET_BETWEEN_SEAS / 2));
        getChildren().add(otherPlayerlabel);


        mainPlayerProgressBar = new ProgressBar();
        mainPlayerProgressBar.setProgress(1.0);
        mainPlayerProgressBar.setPrefSize(200, 20); // Set width and height
        mainPlayerProgressBar.relocate(75, offsetY - 30 + (numRow * GraphicalCell.SIZE) + ((double) OFFSET_BETWEEN_SEAS / 2)); // Set position
        mainPlayerProgressBar.setVisible(false);
        getChildren().add(mainPlayerProgressBar); // Add progress bar to scene

        otherPlayerProgressBar = new ProgressBar();
        otherPlayerProgressBar.setProgress(1.0);
        otherPlayerProgressBar.setPrefSize(200, 20); // Set width and height
        otherPlayerProgressBar.relocate(75, offsetY + (numRow * GraphicalCell.SIZE) + ((double) OFFSET_BETWEEN_SEAS / 2)); // Set position
        otherPlayerProgressBar.setVisible(false);
        getChildren().add(otherPlayerProgressBar); // Add progress bar to scene


        for (int row = 0; row < numRow; row++) {
            Text text = new Text(String.valueOf(row + 1));
            text.setFill(Color.BLUE);
            text.setFont(Font.font(16));
            text.relocate(((double) GraphicalCell.SIZE / 2) - text.getLayoutBounds().getWidth() - 3, ((row + 1) * GraphicalCell.SIZE) - (text.getLayoutBounds().getHeight() / 2));
            getChildren().add(text);

            for (int col = 0; col < numCol; col++) {
                if (row == 0) {
                    Text text2 = new Text(Character.valueOf((char) (col + 65)).toString());
                    text2.setFill(Color.BLUE);
                    text2.setFont(Font.font(16));
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
            text.setFont(Font.font(16));
            text.relocate(((double) GraphicalCell.SIZE / 2) - text.getLayoutBounds().getWidth() - 3, ((row + 1) * GraphicalCell.SIZE) - (text.getLayoutBounds().getHeight() / 2) + GraphicalCell.SIZE * numRow + OFFSET_BETWEEN_SEAS);
            getChildren().add(text);

            for (int col = 0; col < numCol; col++) {
                if (row == 0) {
                    Text text2 = new Text(Character.valueOf((char) (col + 65)).toString());
                    text2.setFill(Color.BLUE);
                    text2.setFont(Font.font(16));
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
            gameInfoLabel.setWrapText(true);
            gameInfoBox.getChildren().add(gameInfoLabel);
        }
    }

    public int getSizeBoard() {
        return numRow;
    }
}
