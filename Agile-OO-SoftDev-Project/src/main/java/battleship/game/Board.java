package battleship.game;

public class Board  {
    private final int sizeCol;
    private final int sizeRow;
    private Cell[][] boardArray;

    public Board(int sizeCol, int sizeRow) {
        this.sizeRow = sizeRow;
        this.sizeCol = sizeCol;
        fillBoard(sizeCol, sizeRow);
    }

    public int getSizeRow() {
        return sizeRow;
    }

    public int getSizeCol() {
        return sizeCol;
    }

    public Cell getCell(Coordinates coords) {
        return boardArray[coords.getRow()][coords.getCol()];
    }


    private Cell[][] fillBoard(int sizeCol, int sizeRow) {
        boardArray = new Cell[this.sizeRow][this.sizeCol];
        for (int col = 0; col < sizeRow; col++) {
            for (int row = 0; row < sizeCol; row++) {
                Cell cell = new Cell(new Coordinates(col, row), CellStatus.OCEAN);
                boardArray[row][col] = cell ;
            }
        }
        return boardArray;
    }

}
