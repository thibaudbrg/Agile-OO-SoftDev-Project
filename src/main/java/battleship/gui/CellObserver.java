package battleship.gui;

import battleship.game.Cell;

public interface CellObserver {
    void cellUpdated(Cell cell);
}
