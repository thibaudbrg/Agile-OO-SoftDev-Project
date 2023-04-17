package battleship.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static battleship.game.Display.printBoard;

public abstract class Player {
    private PlayerId playerId;
    private List<Ship> remainingShips;
    private Board board;
    private Board memory;
    private ArrayList<Coordinates> indexes;
    public Player(List<Ship> remainingShips, Board board , PlayerId playerId) {
        this.remainingShips = remainingShips;
        this.board = board;
        this.playerId = playerId;
        this.memory = new Board(board.getSizeCol(), board.getSizeRow());

        // we need to create the ships here so that the player's ships' list is not empty
        this.indexes = new ArrayList<Coordinates>();
        for(Ship ship : this.remainingShips){
            for(Cell index : ship.getFields()){
                this.indexes.add(index.getCoords());
            }
        }
    }

    public List<Ship> getRemainingShips() {
        return remainingShips;
    }


    public Board getBoard() {
        return board;
    }

    public int getNumberOfRemainingShips() {
        return remainingShips.size();
    }

    public ArrayList<Coordinates> getIndexes() {
        return indexes;
    }




    public void makeMove(Coordinates i, Player player1, Player player2){
        Player opponent = this == player1 ? player2 : player1 ;
        //boolean hit = false;

        // set miss "MISSED" or hit "HIT"
        if(opponent.getIndexes().contains(i)){
            this.getMemory().getCell(i).setCellStatus(CellStatus.HIT);
            //hit = true;

            // check if ship in sunk("SUNK")
            for(Ship ship : opponent.getRemainingShips()){
                boolean sunk = true;

                for(Cell j : ship.getFields()){
                    if(this.getMemory().getCell(j.getCoords()).getCellStatus() == CellStatus.OCEAN){
                        sunk = false;
                        break;
                    }
                }

                if(sunk){
                    for(Cell j : ship.getFields()){
                        this.getMemory().getCell(j.getCoords()).setCellStatus(CellStatus.SUNK);
                    }
                }
            }

        } else {
            this.getMemory().getCell(i).setCellStatus(CellStatus.MISSED);
        }

        // check if game over
        /*boolean game_over = true;

        for(Coordinates j : opponent.getIndexes()){
            if(player.getMemory().getCell(j).getCellStatus() == CellStatus.OCEAN){
                game_over = false;
            }
        }

        this.over = game_over;*/
    }

    public void handleShot(Coordinates coords, Player otherRealPlayer) {
        if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.HIT) {
            System.out.println("Already Hit");
            printBoard(otherRealPlayer.getBoard());
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.OCEAN) {
            System.out.println("Miss !");
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.MISSED);
            printBoard(otherRealPlayer.getBoard());
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.MISSED) {
            System.out.println("Already missed !");
            printBoard(otherRealPlayer.getBoard());
        } else {
            System.out.println("HIT !");
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.HIT);
            Iterator<Ship> iterator = otherRealPlayer.remainingShips.iterator();
            while (iterator.hasNext()) {
                Ship ship = iterator.next();
                if (ship.hasSunk()) {
                    System.out.println("One ship sank ");
                    iterator.remove();
                    System.out.println("There are still " + otherRealPlayer.getNumberOfRemainingShips() + " remaining ");
                }
            }

            printBoard(otherRealPlayer.getBoard());
        }
    }

    public abstract  Coordinates shoot() ;

    public abstract Ship createShip(ShipType shipType);

    public void createShips(List<Ship> shipsPlayer) {
        for (ShipType shipType : ShipType.values()) {
            Ship ship = createShip(shipType);
            shipsPlayer.add(ship);
            System.out.println("//===========" + playerId + " Board===========\\\\");
            printBoard(board);
        }
}

    public PlayerId getPlayerId() {
        return playerId;
    }

    public Board getMemory(){ return memory; }


}
