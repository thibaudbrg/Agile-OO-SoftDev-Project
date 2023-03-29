package battleship.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {

    private Random rn = new Random();
    private int lastRow;
    private int lastColumn;
    public AIPlayer(List<Ship> ships, Board board, PlayerId playerId) {
        super(ships, board, playerId);
        this.lastRow = -1;
        this.lastColumn = -1;

    }

    public void updateMemory(int col, int row, CellStatus status){
        getMemory().getCell(new Coordinates(col, row)).setCellStatus(status);
        if (status == CellStatus.HIT){
            this.lastColumn = col;
            this.lastRow = row;
        }

    }

    @Override
    public Ship createShip(ShipType shipType) {
        System.out.println(getPlayerId() + " places ship");
        System.out.println("You are going to place the ship: " + shipType);
        System.out.println("It has a length of " + shipType.getLabel());

        Ship ship = new Ship(new ArrayList<>(), shipType);
        int col, row;
        boolean result;
        int orientation;

        do {
            col = rn.nextInt(getBoard().getSizeCol());
            row = rn.nextInt(getBoard().getSizeRow());

            orientation = Orientation.ALL.get(rn.nextInt(4)).ordinal() + 1;

            Coordinates coords = new Coordinates(col, row);
            result = getBoard().addShip(coords, ship, Orientation.values()[orientation - 1]);

        }while(!result);


        System.out.println("Good placement!");
        return ship;
    }

    public Coordinates randomShoot() {
        int col;
        int row;
        do {
            col = rn.nextInt(getBoard().getSizeCol());
            row = rn.nextInt(getBoard().getSizeRow());
        } while(getMemory().getCell(new Coordinates(col, row)).getCellStatus() != CellStatus.OCEAN);


        return new Coordinates(col, row);

    }

    @Override
    public Coordinates shoot() {
        int col = 0;
        int row = 0;
        System.out.println("Player " + getPlayerId() + " shoot");
        if (lastRow == -1 && lastColumn == -1) {
            return randomShoot();
        } else {
            do {
                if (lastRow < getBoard().getSizeRow()-1 && lastRow > 0) {
                    if (lastColumn < getBoard().getSizeCol() - 1 && lastColumn > 0) {
                        int choice = rn.nextInt(4);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn + 1;
                                break;
                            case 1:
                                row = lastRow;
                                col = lastColumn - 1;
                                break;
                            case 2:
                                row = lastRow + 1;
                                col = lastColumn;
                                break;
                            case 3:
                                row = lastRow - 1;
                                col = lastColumn;
                                break;
                        }
                    } else if (lastColumn == 0) {
                        int choice = rn.nextInt(3);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn + 1;
                                break;
                            case 1:
                                row = lastRow + 1;
                                col = lastColumn;
                                break;
                            case 2:
                                row = lastRow - 1;
                                col = lastColumn;
                                break;
                        }
                    } else {
                        int choice = rn.nextInt(3);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn - 1;
                                break;
                            case 1:
                                row = lastRow + 1;
                                col = lastColumn;
                                break;
                            case 2:
                                row = lastRow - 1;
                                col = lastColumn;
                                break;
                        }
                    }
                }
                else if(lastRow ==0){
                    if (lastColumn == 0){
                        int choice = rn.nextInt(2);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn +1;
                                break;
                            case 1:
                                row = lastRow + 1;
                                col = lastColumn;
                                break;

                        }
                    } else if (lastColumn ==  getBoard().getSizeCol() - 1){
                        int choice = rn.nextInt(2);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn - 1;
                                break;
                            case 1:
                                row = lastRow + 1;
                                col = lastColumn;
                                break;
                        }

                    } else {
                        int choice = rn.nextInt(3);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn - 1;
                                break;
                            case 1:
                                row = lastRow;
                                col = lastColumn+1;
                                break;
                            case 2:
                                row = lastRow+1;
                                col = lastColumn;
                                break;
                        }
                    }

                } else {
                    if (lastColumn == 0){
                        int choice = rn.nextInt(2);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn +1;
                                break;
                            case 1:
                                row = lastRow - 1;
                                col = lastColumn;
                                break;

                        }
                    } else if (lastColumn ==  getBoard().getSizeCol() - 1){
                        int choice = rn.nextInt(2);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn - 1;
                                break;
                            case 1:
                                row = lastRow - 1;
                                col = lastColumn;
                                break;
                        }

                    } else {
                        int choice = rn.nextInt(3);
                        switch (choice) {
                            case 0:
                                row = lastRow;
                                col = lastColumn - 1;
                                break;
                            case 1:
                                row = lastRow;
                                col = lastColumn+1;
                                break;
                            case 2:
                                row = lastRow-1;
                                col = lastColumn;
                                break;
                        }
                    }

                }

            }while(getMemory().getCell(new Coordinates(col,row)).getCellStatus() != CellStatus.OCEAN);


        }
        return new Coordinates(col, row);

    }

}
