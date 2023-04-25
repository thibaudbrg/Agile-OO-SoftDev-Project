package battleship.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {

    private final Random rn = new Random();
    private boolean isHard;
    private final Board memory;

    public AIPlayer(List<Ship> ships, Board board, PlayerId playerId,boolean isHard) {
        super(ships, board, playerId);
        //by default the AI is random
        this.isHard = isHard;
        this.memory = new Board(board.getNumRow(), board.getNumCol(),false);


    }
    public boolean addShip(ShipType shipType) {
       boolean success = false;
        do {
            int col, row;
            Orientation orientation;
            col = rn.nextInt(getBoard().getNumRow());
            row = rn.nextInt(getBoard().getNumCol());
            Orientation[] orientations =  Orientation.values();
            orientation = orientations[rn.nextInt(orientations.length)];
            Coordinates coords = new Coordinates(col, row);
            success =  this.addShip(shipType,coords,orientation);
        }
       while (!success);

       return true;

    }

    public void addShips() {
        for (ShipType type:ShipType.values()) {
            addShip(type);
        }
    }


    public Coordinates randomAI() {
        //Board memory = this.getMemory();
        ArrayList<Coordinates> unknown = new ArrayList<>();

        for (int col = 0; col < memory.getNumCol(); col++) {
            for (int row = 0; row < memory.getNumRow(); row++) {
                if (memory.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.OCEAN) {
                    unknown.add(new Coordinates(col,row));
                }
            }
        }

        if (unknown.size() > 0) {
            int randomIndex = new Random().nextInt(unknown.size());
            return unknown.get(randomIndex);
        }
        return null;
    }

    public Coordinates basicAI(){
        // setup
        //abstBoard memory = this.getMemory();


        ArrayList<Coordinates> unknown = new ArrayList<>();
        ArrayList<Coordinates> hits = new ArrayList<>();

        for (int col = 0; col < memory.getNumCol(); col++) {
            for (int row = 0; row < memory.getNumRow(); row++) {
                if (memory.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.OCEAN) {
                    unknown.add(new Coordinates(col,row));
                } else if (memory.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.HIT){
                    hits.add(new Coordinates(col,row));
                }
            }
        }

        // search in neighborhood of hits
        ArrayList<Coordinates> unknownNeighboringHits1 = new ArrayList<>();

        for (Coordinates u : unknown) {
            if (hits.contains(new Coordinates(u.getCol() + 1, u.getRow())) || hits.contains(new Coordinates(u.getCol() - 1, u.getRow())) || hits.contains(new Coordinates(u.getCol(), u.getRow() - 1)) || hits.contains(new Coordinates(u.getCol(), u.getRow() + 1))) {
                unknownNeighboringHits1.add(u);
            }
        }

        // pick "OCEAN" square with direct and level-2 neighbor both marked as "HIT"
            //             if(unknownNeighboringHits1.contains(u) && unknownNeighboringHits2.contains(u)){
        for(Coordinates u : unknown){
            if(unknownNeighboringHits1.contains(u)){
                return u;
            }
        }

        // pick "OCEAN" square that has a level-1 neighbor marked as "HIT" and a level-2 neighbor marked as "HIT"
        if(!unknownNeighboringHits1.isEmpty()){
            Coordinates move = unknownNeighboringHits1.get(new Random().nextInt(unknownNeighboringHits1.size()));
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
            return move;
        }

        // random move
        return randomAI();
    }

    public CellStatus handleShot(Player otherRealPlayer){

        CellStatus newCellstatus;
        Coordinates newCord;
        if (isHard == false) {
            newCord = randomAI();
        }
        else{
            newCord = basicAI();
        }
        newCellstatus = handleShot(newCord,otherRealPlayer);
        memory.getCell(newCord).setCellStatus(newCellstatus);
        return newCellstatus;

    }



}