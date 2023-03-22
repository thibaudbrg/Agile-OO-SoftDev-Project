package battleship.game;

public class Cell {
    private final int col;
    private final int row;
    private CellStatus cellStatus;

    public Cell(int col, int row, CellStatus cellStatus) {
        this.col = col;
        this.row = row;
        this.cellStatus = cellStatus;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }
}
