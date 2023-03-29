package battleship.game;

import java.util.Iterator;
import java.util.List;

import static battleship.game.Display.printBoard;

public abstract class Player {
    private PlayerId playerId;
    private List<Ship> remainingShips;
    private Board board;
    public Player(List<Ship> remainingShips, Board board , PlayerId playerId) {
        this.remainingShips = remainingShips;
        this.board = board;
        this.playerId = playerId;
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

}
