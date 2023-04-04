package battleship.game;

public class Cell {
    private final Coordinates coords;
    private CellStatus cellStatus;

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
    }
}
