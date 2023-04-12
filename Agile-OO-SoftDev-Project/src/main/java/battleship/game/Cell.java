package battleship.game;

import battleship.gui.CellObserver;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Coordinates coords;
    private CellStatus cellStatus;
    private final List<CellObserver> observers = new ArrayList<>();

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

    public void addObserver(CellObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (CellObserver observer : observers) {
            observer.cellUpdated(this);
        }
    }
}


