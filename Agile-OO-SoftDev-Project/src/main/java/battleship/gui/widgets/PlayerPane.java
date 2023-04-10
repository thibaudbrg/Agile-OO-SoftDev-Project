package battleship.gui.widgets;

import battleship.game.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class PlayerPane extends Pane {

    private final int sizeCol;
    private final int sizeRow;
    public static final int OFFSET_BETWEEN_SEAS = 70;
    private final int offsetX = GraphicalCell.SIZE / 2;
    private final int offsetY = GraphicalCell.SIZE / 2;

    private Orientation currentOrientation = Orientation.N;

    private final Player player;
    private final Player otherPlayer;

    private ProgressBar progressBar;
    private ProgressBar otherProgressBar;

    ////File winMusic = new File("battleship/gui/sounds/win.mp3");//////////////////////////////
    ////Media wMusic = new Media(winMusic.toURI().toString());//////////////////////////////
    ////MediaPlayer winMediaPlayer = new MediaPlayer(wMusic);//////////////////////////////

    ////File shipMusic = new File("battleship/gui/sounds/place_ship.mp3");//////////////////////////////
    ////Media sMusic = new Media(shipMusic.toURI().toString());//////////////////////////////
    ////MediaPlayer shipMediaPlayer = new MediaPlayer(sMusic);//////////////////////////////

    public PlayerPane(Player player, Player otherPlayer) {

        this.player = player;
        this.otherPlayer = otherPlayer;
        sizeCol = player.getBoard().getSizeCol();
        sizeRow = player.getBoard().getSizeRow();

        setMinSize(sizeRow * GraphicalCell.SIZE + offsetX, 2 * (sizeCol * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS);
        setMaxSize(sizeRow * GraphicalCell.SIZE + offsetX, 2 * (sizeCol * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS);
        setPrefSize(sizeRow * GraphicalCell.SIZE + offsetX, 2 * (sizeCol * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS);
        setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));


        loadBothSea();

        setOnKeyPressed(event -> {
            if (player.amICurrentPlayer()) { // TODO
                requestFocus();
                System.out.println("Key code: " + event.getCode());
                if (event.getCode() == KeyCode.ESCAPE) System.out.println("Escape");
                updateOrientation(event.getCode());
                if (currentOrientation == null) currentOrientation = Orientation.N;
                System.out.println("Pressed" + currentOrientation);
            }
        });


        setOnKeyReleased(event -> {
            if (player.amICurrentPlayer()) { // TODO
                if (!(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT)) {
                    currentOrientation = null;
                }
                System.out.println("Released" + currentOrientation);
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
        mainPlayerlabel.relocate(22, offsetY - 30 + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2));
        getChildren().add(mainPlayerlabel);

        Label otherPlayerlabel = new Label("Down:");
        otherPlayerlabel.setFont(new Font(16));
        otherPlayerlabel.relocate(22, offsetY + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2));
        getChildren().add(otherPlayerlabel);


        progressBar = new ProgressBar();
        progressBar.setProgress(1.0);
        progressBar.setPrefSize(200, 20); // Set width and height
        progressBar.relocate(75, offsetY - 30 + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2)); // Set position
        progressBar.setVisible(false);
        getChildren().add(progressBar); // Add progress bar to scene

        otherProgressBar = new ProgressBar();
        otherProgressBar.setProgress(1.0);
        otherProgressBar.setPrefSize(200, 20); // Set width and height
        otherProgressBar.relocate(75, offsetY + (sizeCol * GraphicalCell.SIZE) + (OFFSET_BETWEEN_SEAS / 2)); // Set position
        otherProgressBar.setVisible(false);
        getChildren().add(otherProgressBar); // Add progress bar to scene


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
                    text2.relocate((col + 1) * GraphicalCell.SIZE, OFFSET_BETWEEN_SEAS + (GraphicalCell.SIZE * sizeRow));
                    getChildren().add(text2);
                }

                GraphicalCell t = new GraphicalCell(player.getBoard().getCell(new Coordinates(col, row)), true);
                t.relocate(offsetX + (GraphicalCell.SIZE * col), offsetY + GraphicalCell.SIZE * sizeCol + OFFSET_BETWEEN_SEAS + (GraphicalCell.SIZE * row));
                getChildren().add(t);
            }
        }
    }

    public int getSizeBoard() {
        return sizeCol;
    }

    public void setProgressBarsVisible(boolean visible) {
        progressBar.setVisible(visible);
        otherProgressBar.setVisible(visible);
    }

    public void setProgressBarsProgress(double playerChange, double otherPlayerChange) {
        progressBar.setProgress(playerChange);
        otherProgressBar.setProgress(otherPlayerChange);
    }
}
