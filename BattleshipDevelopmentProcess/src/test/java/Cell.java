public class Cell {
    private int row;
    private int col;
    private CellStatus status;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.status = CellStatus.OCEAN;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    // other functions and getters/setters...
}
