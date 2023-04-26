package battleship.game;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Coordinates coords;
    private CellStatus cellStatus;
    private final List<CellListener> observers = new ArrayList<>();

    public Cell(Coordinates coords, CellStatus cellStatus) {
        this.coords = coords;
        this.cellStatus = cellStatus;
    }

    public Coordinates getCoords() {
        return coords;
    }
    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
        notifyObservers();
    }

    public void addObserver(CellListener observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (CellListener observer : observers) {
            observer.cellUpdated(this);
        }
    }
}


