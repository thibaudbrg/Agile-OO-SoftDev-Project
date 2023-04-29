package battleship.game;

import java.util.Random;

public class Board {
    private final int numRow;
    private final int numCol;
    private final boolean withRock;


    private Cell[][] boardArray;


    public Board(int numRow, int numCol, boolean withRock) {
        this.numCol = numCol;
        this.numRow = numRow;
        this.withRock = withRock;
        fillBoard();
    }

    public int getNumCol() {
        return numCol;
    }

    public int getNumRow() {
        return numRow;
    }

    public Cell getCell(Coordinates coords) {
        return boardArray[coords.getRow()][coords.getCol()];
    }


    private Cell[][] fillBoard() {
        boardArray = new Cell[numRow][numCol];
        Random random = new Random();
        int rockCount = 0;
        for (int col = 0; col < numCol; col++) {
            for (int row = 0; row < numRow; row++) {
                Cell cell = new Cell(new Coordinates(col, row), CellStatus.OCEAN);
                boardArray[row][col] = cell;
            }
        }
        if (withRock) {
            while (rockCount < 2) {
                int randomRow = random.nextInt(numRow);
                int randomCol = random.nextInt(numCol);
                Cell cell = boardArray[randomRow][randomCol];
                if (cell.getCellStatus() == CellStatus.OCEAN) {
                    cell.setCellStatus(CellStatus.ROCK);
                    rockCount++;
                }
            }
        }


        return boardArray;
    }


}
