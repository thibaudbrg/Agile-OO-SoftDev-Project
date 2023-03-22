package battleship.game;
import java.util.Iterator;
import java.util.List;

import static battleship.game.Display.printBoard;

public class Player {
    private List<Ship> remainingShips;
    private Board board;

    public Player(List<Ship> remainingShips, Board board) {
        this.remainingShips = remainingShips;
        this.board = board;

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

    public void handleShot(int col , int row ,Player otherPlayer){
        if(otherPlayer.getBoard().getCell(col,row).getCellStatus()==CellStatus.HIT){
            System.out.println("already Hit");
            printBoard(otherPlayer.getBoard());
        } else if (otherPlayer.getBoard().getCell(col,row).getCellStatus()==CellStatus.OCEAN) {
            System.out.println("miss !");
            otherPlayer.getBoard().getCell(col,row).setCellStatus(CellStatus.MISSED);
            printBoard(otherPlayer.getBoard());
        } else if (otherPlayer.getBoard().getCell(col,row).getCellStatus()==CellStatus.MISSED) {
            System.out.println("already missed !");
            printBoard(otherPlayer.getBoard());
        } else {
            System.out.println("HIT !");
            otherPlayer.getBoard().getCell(col,row).setCellStatus(CellStatus.HIT);
            Iterator<Ship> iterator = otherPlayer.remainingShips.iterator();
            while(iterator.hasNext()) {
                Ship ship = iterator.next();
                if(ship.hasSunk()){
                    System.out.println("one ship has sunk ");
                    iterator.remove();
                    System.out.println("there is still "+ otherPlayer.getNumberOfRemainingShips() +" remaining " );
                }
            }

            printBoard(otherPlayer.getBoard());
        }
    }

}

// remainingShips to handle
