package battleship.game;

import java.util.List;

public class Ship {
    private final List<Cell> shipCells;
    private final ShipType shipType;

    public Ship(List<Cell> shipCells, ShipType shipType) {
        this.shipCells = shipCells;
        this.shipType = shipType;
    }

    public List<Cell> getFields() {
        return shipCells;
    }

    public boolean hasSunk() {
        for (Cell cell : shipCells) {
            if (!cell.getCellStatus().equals(CellStatus.HIT)) {
                return false;
            }
        }
        return true;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void add(Cell cell) {
        shipCells.add(cell);
    }
}
