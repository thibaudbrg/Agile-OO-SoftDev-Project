public class Board {
    private final int numRows;
    private final int numCols
            ;
    private Cell[][] cells;

    public Board(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;

        cells = new Cell[numRows][numCols];
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public void placeShip(Ship ship) {
        for (Cell cell : ship.getCells()) {
            int row = cell.getRow();
            int col = cell.getCol();
            cells[row][col].setStatus(CellStatus.SHIP);
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
}
