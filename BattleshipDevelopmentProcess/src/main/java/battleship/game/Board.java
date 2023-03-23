package battleship.game;

import java.util.ArrayList;
import java.util.List;


public class Board {
    private final int sizeCol;
    private final int sizeRow;
    Cell[][] boardArray;

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

    public Cell[][] fillBoard(int sizeCol, int sizeRow) {
        boardArray = new Cell[this.sizeRow][this.sizeCol];
        for (int col = 0; col < sizeRow; col++) {
            for (int row = 0; row < sizeCol; row++) {
                boardArray[col][row] = new Cell(new Coordinates(col, row), CellStatus.OCEAN);
            }
        }
        return boardArray;
    }

    public boolean addShip(Cell cell, Ship ship, Orientation orientation) {
        int col = cell.getCoords().getCol();
        int row = cell.getCoords().getRow();
        int sizeShip = ship.getShipType().label;

        List<Cell> cellsToAdd = new ArrayList<>();
        cellsToAdd.add(cell);

        int[][] orientationChanges = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int[] changes = orientationChanges[orientation.ordinal()];

        for (int i = 1; i < sizeShip; i++) {
            col += changes[0];
            row += changes[1];

            if (!isInsideBoard(col, row) || boardArray[row][col].getCellStatus() == CellStatus.SHIP) {
                System.out.println("The ship collides with another ship or is out of the board! Try again.");
                return false;
            }

            cellsToAdd.add(boardArray[row][col]);
        }

        for (Cell cellToAdd : cellsToAdd) {
            cellToAdd.setCellStatus(CellStatus.SHIP);
            ship.add(cellToAdd);
        }

        return true;
    }

    private boolean isInsideBoard(int col, int row) {
        return col >= 0 && col < sizeRow && row >= 0 && row < sizeCol;
    }
}