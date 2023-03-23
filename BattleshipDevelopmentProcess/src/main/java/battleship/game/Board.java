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
        fillBoard(sizeRow, sizeCol);
    }

    public int getSizeRow() {
        return sizeRow;
    }

    public int getSizeCol() {
        return sizeCol;
    }

    public Cell getCell(int coll, int row) {
        return boardArray[row][coll];
    }

    public Cell[][] fillBoard(int sizeRow, int sizeCol) {
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
        int size = ship.getShipType().label;

        List<Cell> cellsToAdd = new ArrayList<>();
        cellsToAdd.add(cell);

        if (checkIfInsideBoard(col, row, size, orientation, sizeRow, sizeCol)) {
            for (int i = 1; i < size; i++) {
                switch (orientation) {
                    case N:
                        row -= 1;
                        break;
                    case S:
                        row += 1;
                        break;
                    case E:
                        col += 1;
                        break;
                    case W:
                        col -= 1;
                        break;
                }
                if (boardArray[row][col].getCellStatus() == CellStatus.SHIP) {
                    System.out.println("The ship collides with another ship! Try again.");
                    return false;
                }
                cellsToAdd.add(boardArray[row][col]);
            }

            // Need to be done outside the first loop because all cells must be okay before creating the ship
            for (Cell cellToAdd : cellsToAdd) {
                cellToAdd.setCellStatus(CellStatus.SHIP);
                ship.add(cellToAdd);
                boardArray[cellToAdd.getCoords().getRow()][cellToAdd.getCoords().getCol()] = cellToAdd;
            }
            return true;
        }
        return false;
    }

    private boolean checkIfInsideBoard(int col, int row, int size, Orientation orientation, int sizeCol,
                                       int sizeRow) {
        int endCol = col, endRow = row;
        switch (orientation) {
            case N:
                endRow -= size - 1;
                break;
            case S:
                endRow += size - 1;
                break;
            case E:
                endCol += size - 1;
                break;
            case W:
                endCol -= size - 1;
                break;
        }
        if (endCol < 0 || endRow < 0 || endCol >= sizeCol || endRow >= sizeRow) {
            System.out.println("The ship is out of the board! Try again.");
            return false;
        } else {
            return true;
        }
    }
}