package battleship.game;

import java.util.List;

public class Ship {
    private final List<Cell> NewShip;
    private final ShipType shipType;

    public Ship(List<Cell> newShip, ShipType shipType) {
        NewShip = newShip;
        this.shipType = shipType;
    }

    public List<Cell> getFields() {
        return NewShip;
    }
    
    public ShipType getShipType() {
        return shipType;
    }

    public void add(Cell cell) {
        NewShip.add(cell);
    }
}
