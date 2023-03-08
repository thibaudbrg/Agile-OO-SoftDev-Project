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

    public boolean isPlacementOK(Ship ship1, List<Ship> allShips, Board board) {
        int count = 0;

        for (int i = 0; i < ship1.getFields().size(); i++) {
            if (ship1.getFields().get(i).getRow() > board.getSizeCol() ||
                    ship1.getFields().get(i).getCol() > board.getSizeRow()) {
                ++count;
            }

            for (int j = 0; j < allShips.size(); j++) {
                for (int k = 0; k < allShips.get(j).getFields().size(); k++) {
                    if (ship1.getFields().get(i).getCol() == allShips.get(j).getFields().get(k).getCol() &&
                            ship1.getFields().get(i).getRow() == allShips.get(j).getFields().get(k).getRow()) {
                        ++count;
                    }
                }
            }
        }

        return count == 0;
    }
}
