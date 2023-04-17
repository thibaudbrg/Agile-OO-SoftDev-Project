package battleship.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {

    private final Random rn = new Random();
    //private int lastRow;
    //private int lastColumn;
    private int type;
    public AIPlayer(List<Ship> ships, Board board, PlayerId playerId) {
        super(ships, board, playerId);
        //by default the AI is random
        this.type = 1;

    }

    /*public void updateMemory(int col, int row, CellStatus status){
        getMemory().getCell(new Coordinates(col, row)).setCellStatus(status);
        if (status == CellStatus.HIT){
            this.lastColumn = col;
            this.lastRow = row;
        }

    }*/

    @Override
    public Ship createShip(ShipType shipType) {
        System.out.println(getPlayerId() + " places ship");
        System.out.println("You are going to place the ship: " + shipType);
        System.out.println("It has a length of " + shipType.getSize());

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

    public Coordinates randomAI() {
        Board memory = this.getMemory();
        ArrayList<Coordinates> unknown = new ArrayList<>();

        for (int col = 0; col < memory.getSizeCol(); col++) {
            for (int row = 0; row < memory.getSizeRow(); row++) {
                if (memory.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.OCEAN) {
                    unknown.add(new Coordinates(col,row));
                }
            }
        }

        if (unknown.size() > 0) {
            int randomIndex = new Random().nextInt(unknown.size());
            //this.makeMove(unknown.get(randomIndex),player1,player2);
            return unknown.get(randomIndex);
        }
        return null;
    }

    public Coordinates basicAI(){
        // setup
        Board memory = this.getMemory();
        ArrayList<Coordinates> unknown = new ArrayList<>();
        ArrayList<Coordinates> hits = new ArrayList<>();

        for (int col = 0; col < memory.getSizeCol(); col++) {
            for (int row = 0; row < memory.getSizeRow(); row++) {
                if (memory.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.OCEAN) {
                    unknown.add(new Coordinates(col,row));
                } else if (memory.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.HIT){
                    hits.add(new Coordinates(col,row));
                }
            }
        }

        // search in neighborhood of hits
        ArrayList<Coordinates> unknownNeighboringHits1 = new ArrayList<>();
        ArrayList<Coordinates> unknownNeighboringHits2 = new ArrayList<>();

        for (Coordinates u : unknown){
            if(hits.contains(new Coordinates(u.getCol() +1, u.getRow())) || hits.contains(new Coordinates(u.getCol() -1, u.getRow())) || hits.contains(new Coordinates(u.getCol(), u.getRow()-1)) || hits.contains(new Coordinates(u.getCol(), u.getRow()+1))){
                unknownNeighboringHits1.add(u);
            }

            if(hits.contains(new Coordinates(u.getCol() +2, u.getRow())) || hits.contains(new Coordinates(u.getCol() -2, u.getRow())) || hits.contains(new Coordinates(u.getCol(), u.getRow()-2)) || hits.contains(new Coordinates(u.getCol(), u.getRow()+2))){
                unknownNeighboringHits2.add(u);
            }
        }

        // pick "OCEAN" square with direct and level-2 neighbor both marked as "HIT"
        for(Coordinates u : unknown){
            if(unknownNeighboringHits1.contains(u) && unknownNeighboringHits2.contains(u)){
                //makeMove(u,player1,player2);
                return u;
            }
        }

        // pick "OCEAN" square that has a level-1 neighbor marked as "HIT" and a level-2 neighbor marked as "HIT"
        if(!unknownNeighboringHits1.isEmpty()){
            Coordinates move = unknownNeighboringHits1.get(new Random().nextInt(unknownNeighboringHits1.size()));
            //makeMove(move,player1,player2);
            return move;
        }

        // checker board pattern
        ArrayList<Coordinates> checkerBoard = new ArrayList<>();
        for(Coordinates u : unknown){
            if((u.getRow() + u.getCol()) % 2 == 0){
                checkerBoard.add(u);
            }
        }

        if(!checkerBoard.isEmpty()){
            Coordinates move = checkerBoard.get(new Random().nextInt(checkerBoard.size()));
            //makeMove(move,player1,player2);
            return move;
        }

        // random move
        return randomAI();
    }

    @Override
    public Coordinates shoot() {
        if (type ==1) return randomAI();
        return basicAI();
    }
    public void setType(int type) {
        this.type = type;
    }

    /*public Coordinates randomShoot() {
        int col;
        int row;
        do {
            col = rn.nextInt(getBoard().getSizeCol());
            row = rn.nextInt(getBoard().getSizeRow());
        } while(getMemory().getCell(new Coordinates(col, row)).getCellStatus() != CellStatus.OCEAN);


        return new Coordinates(col, row);

    }*/

    /*@Override
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

    }*/

}
