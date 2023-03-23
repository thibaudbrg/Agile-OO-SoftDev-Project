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

    public void handleShot(Coordinates coords, Player otherPlayer) {
        if (otherPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.HIT) {
            System.out.println("Already Hit");
            printBoard(otherPlayer.getBoard());
        } else if (otherPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.OCEAN) {
            System.out.println("Miss !");
            otherPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.MISSED);
            printBoard(otherPlayer.getBoard());
        } else if (otherPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.MISSED) {
            System.out.println("Already missed !");
            printBoard(otherPlayer.getBoard());
        } else {
            System.out.println("HIT !");
            otherPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.HIT);
            Iterator<Ship> iterator = otherPlayer.remainingShips.iterator();
            while (iterator.hasNext()) {
                Ship ship = iterator.next();
                if (ship.hasSunk()) {
                    System.out.println("One ship sank ");
                    iterator.remove();
                    System.out.println("There are still " + otherPlayer.getNumberOfRemainingShips() + " remaining ");
                }
            }

            printBoard(otherPlayer.getBoard());
        }
    }

}

