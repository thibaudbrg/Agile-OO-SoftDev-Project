package battleship.gui;

import java.util.Objects;

import battleship.game.Cell;
import battleship.game.CellListener;
import battleship.game.Coordinates;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class GraphicalCell extends Canvas implements CellListener {

    public static final int SIZE = 30;
    private final int id;
    private boolean mouseOver;
    private final Cell cell;
    private final boolean isItMine;

    public GraphicalCell(Cell cell, boolean isItMine) {
        super(SIZE, SIZE);
        this.id = IdGenerator.get();
        this.mouseOver = false;
        this.cell = cell;
        this.isItMine = isItMine;
        cell.addObserver(this);
        redraw();

        setOnMouseEntered((EventHandler<Event>) event -> setMouseOver(true));
        setOnMouseExited((EventHandler<Event>) event -> setMouseOver(false));

    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
        redraw();
    }

    public Cell getCell(){ return cell; }

    public Coordinates getCoordinates() {
        return cell.getCoords();
    }

    public void redraw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, SIZE, SIZE);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, SIZE, SIZE);

        if (mouseOver) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(3);
            gc.strokeRoundRect(3, 3, SIZE - 6, SIZE - 6, 10, 10);
        }

        switch (cell.getCellStatus()) {
            case HIT -> gc.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
            case MISSED -> gc.setFill(Color.BLACK.deriveColor(0, 1, 1, 0.5));
            case SHIP -> {
                if (isItMine) {
                    gc.setFill(Color.GRAY.deriveColor(0, 1, 1, 0.5));
                } else {
                    gc.setFill(Color.rgb(62, 148, 247, 0.5));
                }
            }
            case OCEAN -> gc.setFill(Color.rgb(62, 148, 247, 0.5));
            case ROCK -> {
                if (isItMine) {
                    Image rockImage = new Image("assets/rock.png");
                    ImagePattern pattern = new ImagePattern(rockImage);
                    gc.setFill(pattern);
                } else {
                    gc.setFill(Color.rgb(62, 148, 247, 0.5));
                }
            }
            case ROCK_HIT -> {
                Image rockHitImage = new Image("assets/rockHit.png");
                gc.setFill(new ImagePattern(rockHitImage));
            }
        }
        gc.fillRect(0, 0, SIZE, SIZE);

    }


    public void cellUpdated(Cell cell){
        redraw();
    }


    public boolean isMine() {
        return isItMine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphicalCell) {
            return this.id == ((GraphicalCell) obj).id;
        }
        return false;
    }


}
