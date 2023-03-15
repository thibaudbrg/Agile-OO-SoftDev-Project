package battleship.game;

import java.util.List;

public class Ship {
    private final List<Cell> shipCells;
    private final ShipType shipType;

    //private boolean hasSinked ;

    public Ship(List<Cell> shipCells, ShipType shipType) {
        this.shipCells = shipCells;
        this.shipType = shipType;
        //this.hasSinked = false ;
    }

    public List<Cell> getFields() {
        return shipCells;
    }

    public boolean HasSinked(){
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
