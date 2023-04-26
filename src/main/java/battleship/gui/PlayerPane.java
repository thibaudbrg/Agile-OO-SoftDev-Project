package battleship.gui;

import battleship.game.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;

public class PlayerPane extends Pane implements PlayerListener {

    public static final int OFFSET_BETWEEN_SEAS = 70;


    private final int numRow;
    private final int numCol;
    private final int offsetX = GraphicalCell.SIZE / 2;
    private final int offsetY = GraphicalCell.SIZE / 2;
    private final int infoSize = 100;

    private final Player player;
    private final Player otherPlayer;

    private final boolean isTimed;

    private VBox gameInfoBox;
    private VBox gameTimerBox;
    private ProgressBar mainPlayerProgressBar;

    public PlayerPane(Player player, Player otherPlayer, Boolean isTimed) {
        this.player = player;
        this.otherPlayer = otherPlayer;
        numRow = player.getBoard().getNumRow();
        numCol = player.getBoard().getNumCol();
        this.isTimed = isTimed;
        this.player.addObserver(this);

        setMinSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setMaxSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setPrefSize(numCol * GraphicalCell.SIZE + 2 * offsetX, 2 * (numRow * GraphicalCell.SIZE + offsetY) + OFFSET_BETWEEN_SEAS + infoSize);
        setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        loadBothSea(isTimed);
    }

    public Player getPlayer() {
        return player;
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

                GraphicalCell t = new GraphicalCell(player.getBoard().getCell(new Coordinates(col, row)), true);
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

    public void updateGameInfo(LinkedList<String> infos) {
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

    public void updateTimer(int newTimerValue) {
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
    }

    @Override
    public void onRemainingShipsChanged(int remainingShips) {
        System.out.println("yes "+ remainingShips);
        mainPlayerProgressBar.setProgress(1 - remainingShips / (double) ShipType.values().length);
    }
}