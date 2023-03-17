import java.util.List;

public class Ship {
    private List<Cell> cells;
    private final int length;
    private final ShipType type;

    public Ship(List<Cell> cells, ShipType type) {
        this.cells = cells;
        this.length = cells.size();
        this.type = type;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public int getLength() {
        return length;
    }

    public ShipType getType() {
        return type;
    }

    // other functions and getters/setters...
}
