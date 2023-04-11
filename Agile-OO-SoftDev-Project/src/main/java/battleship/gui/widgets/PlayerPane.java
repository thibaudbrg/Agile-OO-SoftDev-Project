package battleship.gui.widgets;

import battleship.game.*;
import battleship.gui.GameInfo;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;


public class PlayerPane extends Pane implements Observer {

    private int sizeCol;
    private int sizeRow;
    public static final int OFFSET_BETWEEN_SEAS = 70;
    private int offsetX = GraphicalCell.SIZE / 2;
    private int offsetY = GraphicalCell.SIZE / 2;

    private int infoSize= 100 ;

    private Orientation currentOrientation = Orientation.N;

    private int shipPlacedCounter;

    private Player mainPlayer;
    private Player otherPlayer;
    VBox gameInfoBox;

    ProgressBar mainPlayerProgressBar;
    ProgressBar otherPlayerProgressBar;
    boolean theOtherPlayerAlreadyWon = false;




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
        sizeCol = mainPlayer.getBoard().getSizeCol();
        sizeRow = mainPlayer.getBoard().getSizeRow();
        mainPlayer.getGameInfo().addObserver(this);

        setMinSize(sizeRow * GraphicalCell.SIZE + 2*offsetX, 2 * (sizeCol * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setMaxSize(sizeRow * GraphicalCell.SIZE + 2*offsetX, 2 * (sizeCol * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setPrefSize(sizeRow * GraphicalCell.SIZE + 2*offsetX, 2 * (sizeCol * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS+ infoSize);
        setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

/*
        setBackground(
                new Background(
                        new BackgroundImage(
                                new Image(Objects.requireNonNull(PlayerPane.class.getClassLoader().getResourceAsStream("assets/background.png"))),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true),
                                BackgroundSize.DEFAULT)));

 */
        loadBothSea();
        if (mainPlayer.getPlayerId() == PlayerId.PLAYER_1) {
            mainPlayer.setAmICurrentPlayer(true);
            otherPlayer.setAmICurrentPlayer(false); // TODO SHOULD BE USELESS
        }

        this.setOnKeyPressed(event -> {
            if (mainPlayer.amICurrentPlayer()) {
                if (currentOrientation == null) currentOrientation = Orientation.N;
                updateOrientation(event.getCode());
            }
        });

        setOnMouseClicked(event -> {
            if (mainPlayer.amICurrentPlayer()) {
                requestFocus();
                //System.out.println("Just Clicked");
                if ((event.getButton() == MouseButton.PRIMARY) && (event.getTarget() instanceof GraphicalCell gCell)) {
                    switch (GameProgression.whichProgression(shipPlacedCounter)) {
                        case SHIP_PLACEMENT:
                            if (gCell.isMine()) { // TODO condition about nonNull orientation

                                if (currentOrientation != null && mainPlayer.addShip(ShipType.values()[shipPlacedCounter], gCell.getCoordinates(), currentOrientation)) {
                                    ////shipMediaPlayer.setAutoPlay(true);//////////////////////////////
                                    ////shipMediaPlayer.play();//////////////////////////////
                                    //mainPlayer.getGameInfo().addInfo("Good Placement!");

                                    ++shipPlacedCounter;
                                    if (shipPlacedCounter == ShipType.values().length) { // TODO REFACTOR AND PRETTIER
                                        otherPlayer.setAmICurrentPlayer(true);
                                        mainPlayer.setAmICurrentPlayer(false);
                                    }
                                }
                            }
                            break;
                        case PLAYING_GAME:
                            mainPlayerProgressBar.setVisible(true);
                            otherPlayerProgressBar.setVisible(true);
                            Alert alert = null;
                            ButtonType buttonType = null;
                            Optional<ButtonType> result = null;
                            if (mainPlayer.getNumberOfRemainingShips() == 0) {
                                theOtherPlayerAlreadyWon = true;
                                //winMediaPlayer.setAutoPlay(true);//////////////////////////////
                                //winMediaPlayer.play();//////////////////////////////
                                alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText("the other player: " + otherPlayer.getPlayerId() + " Wins!!");
                                //System.out.println(mainPlayer.getPlayerId() + " Wins!!");
                                alert.setContentText("The game is over. Click on the button to move back to the game menu.");
                                buttonType = new ButtonType("OK");
                                alert.getButtonTypes().setAll(buttonType);
                                result = alert.showAndWait();
                                if (result.get() == buttonType) {
                                    Stage stage = (Stage) this.getScene().getWindow();
                                    stage.close();
                                    Main.main(new String[]{});
                                }
                            }
                            if (!gCell.isMine()) {
                                mainPlayer.handleShot(gCell.getCoordinates(), otherPlayer);
                                if (otherPlayer.getNumberOfRemainingShips() == 0 && !theOtherPlayerAlreadyWon) {
                                    //winMediaPlayer.setAutoPlay(true);//////////////////////////////
                                    //winMediaPlayer.play();//////////////////////////////
                                    alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setHeaderText("YOU, " + mainPlayer.getPlayerId() + " Win!!");
                                    //System.out.println(mainPlayer.getPlayerId() + " Wins!!");
                                    alert.setContentText("The game is over. Click on the button to move back to the game menu.");
                                    buttonType = new ButtonType("OK");
                                    alert.getButtonTypes().setAll(buttonType);
                                    result = alert.showAndWait();
                                    if (result.get() == buttonType) {
                                        Stage stage = (Stage) this.getScene().getWindow();
                                        stage.close();
                                        Main.main(new String[]{});

                                    }
                                otherPlayer.setAmICurrentPlayer(false);
                                mainPlayer.setAmICurrentPlayer(false);
                                }
                                mainPlayerProgressBar.setProgress((double) mainPlayer.getNumberOfRemainingShips() / ShipType.values().length);
                                otherPlayerProgressBar.setProgress((double) otherPlayer.getNumberOfRemainingShips() / ShipType.values().length);
                                otherPlayer.setAmICurrentPlayer(true);
                                mainPlayer.setAmICurrentPlayer(false);
                            }


                    }
                }
            }
        });
    }

    private Orientation updateOrientation(KeyCode code) {
        switch (code) {
            case A:
                currentOrientation = Orientation.N;
                System.out.println("n");
                break;
            case DOWN:
                currentOrientation = Orientation.S;
                System.out.println("s");
                break;
            case LEFT:
                currentOrientation = Orientation.W;
                System.out.println("w");
                break;
            case RIGHT:
                currentOrientation = Orientation.E;
                System.out.println("e");
                break;
            default:
                break;
        }
        return currentOrientation;
    }

    private void loadBothSea() {

        Label mainPlayerlabel = new Label("Up:");
        mainPlayerlabel.setFont(new Font(16));
        mainPlayerlabel.relocate(22,offsetY - 30 + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2));
        getChildren().add(mainPlayerlabel);

        Label otherPlayerlabel = new Label("Down:");
        otherPlayerlabel.setFont(new Font(16));
        otherPlayerlabel.relocate(22,offsetY + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2));
        getChildren().add(otherPlayerlabel);



        mainPlayerProgressBar = new ProgressBar();
        mainPlayerProgressBar.setProgress(1.0);
        mainPlayerProgressBar.setPrefSize(200, 20); // Set width and height
        mainPlayerProgressBar.relocate(75, offsetY - 30 + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2)); // Set position
        mainPlayerProgressBar.setVisible(false);
        getChildren().add(mainPlayerProgressBar); // Add progress bar to scene

        otherPlayerProgressBar = new ProgressBar();
        otherPlayerProgressBar.setProgress(1.0);
        otherPlayerProgressBar.setPrefSize(200, 20); // Set width and height
        otherPlayerProgressBar.relocate(75, offsetY + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2)); // Set position
        otherPlayerProgressBar.setVisible(false);
        getChildren().add(otherPlayerProgressBar); // Add progress bar to scene


        for (int row = 0; row < sizeCol; row++) {
            Text text = new Text(String.valueOf(row + 1));
            text.setFill(Color.BLUE);
            text.setFont(Font.font(16));
            text.relocate(
                    (GraphicalCell.SIZE / 2) - text.getLayoutBounds().getWidth() - 3,
                    ((row + 1) * GraphicalCell.SIZE) - (text.getLayoutBounds().getHeight() / 2));
            getChildren().add(text);

            for (int col = 0; col < sizeRow; col++) {
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

        for (int row = 0; row < sizeCol; row++) {
            Text text = new Text(String.valueOf(row + 1));
            text.setFill(Color.BLUE);
            text.setFont(Font.font(16));
            text.relocate(
                    (GraphicalCell.SIZE / 2) - text.getLayoutBounds().getWidth() - 3,
                    ((row + 1) * GraphicalCell.SIZE) - (text.getLayoutBounds().getHeight() / 2) + GraphicalCell.SIZE * sizeCol + OFFSET_BETWEEN_SEAS);
            getChildren().add(text);

            for (int col = 0; col < sizeRow; col++) {
                if (row == 0) {
                    Text text2 = new Text(Character.valueOf((char) (col + 65)).toString());
                    text2.setFill(Color.BLUE);
                    text2.setFont(Font.font(16));
                    text2.relocate((col + 1) * GraphicalCell.SIZE , OFFSET_BETWEEN_SEAS + (GraphicalCell.SIZE * sizeRow));
                    getChildren().add(text2);
                }

                GraphicalCell t = new GraphicalCell(mainPlayer.getBoard().getCell(new Coordinates(col, row)), true);
                t.relocate(offsetX + (GraphicalCell.SIZE * col), offsetY + GraphicalCell.SIZE * sizeCol + OFFSET_BETWEEN_SEAS + (GraphicalCell.SIZE * row));
                getChildren().add(t);
            }
        }


        gameInfoBox = new VBox();
        gameInfoBox.setStyle("-fx-background-color: white;");
        gameInfoBox.setPrefSize(2*offsetX + (GraphicalCell.SIZE * sizeRow), infoSize);
        gameInfoBox.relocate(0,2*GraphicalCell.SIZE*sizeCol+2*offsetY+OFFSET_BETWEEN_SEAS);
        getChildren().add(gameInfoBox);
        update(mainPlayer.getGameInfo(),new Object());
    }

    @Override
    public void update(Observable o, Object arg) {
        GameInfo info = (GameInfo) o;
        Label titleLabel = new Label("Game Info");
        titleLabel.setFont(new Font(18));

        Label gameNewInfoLabel = new Label("- "+info.getNewInfo());
        gameNewInfoLabel.setFont(new Font(12));
        gameNewInfoLabel.setWrapText(true);
        gameNewInfoLabel.setMaxWidth(2*GraphicalCell.SIZE*sizeCol+2*offsetY+OFFSET_BETWEEN_SEAS-5);

        Label gameLastInfoLabel = new Label("- " + info.getLastInfo());
        gameLastInfoLabel.setFont(new Font(12));
        gameLastInfoLabel.setWrapText(true);
        gameLastInfoLabel.setMaxWidth(2*GraphicalCell.SIZE*sizeCol+2*offsetY+OFFSET_BETWEEN_SEAS-5);


        gameInfoBox.getChildren().clear();
        gameInfoBox.getChildren().addAll(titleLabel, gameNewInfoLabel,gameLastInfoLabel);
    }



    public int getSizeBoard() {
        return sizeCol;
    }
}
