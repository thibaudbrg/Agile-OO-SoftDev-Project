package battleship.gui.widgets;

import java.util.Objects;

import battleship.game.Cell;
import battleship.gui.CellObserver;
import battleship.game.Coordinates;
import battleship.gui.IdGenerator;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphicalCell extends Canvas implements CellObserver {

    public static final int SIZE = 35;
    private int id;
    private boolean mouseOver;
    private Cell cell;
    private final boolean isItMine;

    public GraphicalCell(Cell cell, boolean isItMine) {
        super(SIZE, SIZE);
        this.id = IdGenerator.get();
        this.mouseOver = false;
        this.cell = cell;
        this.isItMine = isItMine;
        cell.addObserver(this);
        redraw();

        setOnMouseEntered(new EventHandler<Event>() {
            @Override
            public void handle(Event event) { setMouseOver(true); }
        });
        setOnMouseExited(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                setMouseOver(false);
            }
        });

    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
        redraw();
    }

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
            case HIT:
                gc.setFill(Color.RED.deriveColor(0, 1, 1, 0.5));
                break;
            case MISSED:
                gc.setFill(Color.BLACK.deriveColor(0, 1, 1, 0.5));
                break;
            case SHIP:
                if (isItMine) {
                    gc.setFill(Color.GRAY.deriveColor(0, 1, 1, 0.5));
                } else {
                    gc.setFill(Color.BLUE.deriveColor(0, 1, 1, 0.5));
                }
                break;
            case OCEAN:
                    gc.setFill(Color.BLUE.deriveColor(0, 1, 1, 0.5));
                break;
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
