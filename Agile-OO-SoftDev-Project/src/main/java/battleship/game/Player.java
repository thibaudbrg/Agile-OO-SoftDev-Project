package battleship.game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static battleship.game.Display.printBoard;

public abstract class Player {
    //File missMusic = new File("battleship/gui/sounds/miss.mp3");//////////////////////////////
    //Media mMusic = new Media(missMusic.toURI().toString());//////////////////////////////
    //MediaPlayer missMediaPlayer = new MediaPlayer(mMusic);//////////////////////////////
    //File hitMusic = new File("battleship/gui/sounds/hit.mp3");//////////////////////////////
    //Media hMusic = new Media(hitMusic.toURI().toString());//////////////////////////////
    //MediaPlayer hitMediaPlayer = new MediaPlayer(hMusic);//////////////////////////////
    private PlayerId playerId;
    private List<Ship> remainingShips;
    private Board board;
    private Board memory;
    private boolean amICurrentPlayer;
    public Player(List<Ship> remainingShips, Board board, PlayerId playerId) {
        this.remainingShips = remainingShips;
        this.board = board;
        this.playerId = playerId;
        this.memory = new Board(board.getSizeCol(), board.getSizeRow());
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
        CellStatus newStatus = CellStatus.OCEAN;
        if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.HIT) {
            newStatus = CellStatus.HIT;
            //missMediaPlayer.setAutoPlay(true);//////////////////////////////
            //missMediaPlayer.play();//////////////////////////////
            System.out.println("Already Hit");
            printBoard(otherRealPlayer.getBoard());
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.OCEAN) {
            //missMediaPlayer.setAutoPlay(true);//////////////////////////////
            //missMediaPlayer.play();//////////////////////////////
            newStatus = CellStatus.MISSED;
            System.out.println("Miss !");
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.MISSED);
            printBoard(otherRealPlayer.getBoard());
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.MISSED) {
            newStatus = CellStatus.MISSED;
            //missMediaPlayer.setAutoPlay(true);//////////////////////////////
            //missMediaPlayer.play();//////////////////////////////
            System.out.println("Already missed !");
            printBoard(otherRealPlayer.getBoard());
        } else {
            //hitMediaPlayer.setAutoPlay(true);//////////////////////////////
            //hitMediaPlayer.play();//////////////////////////////
            newStatus = CellStatus.HIT;
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
            memory.getCell(coords).setCellStatus(newStatus);
            printBoard(otherRealPlayer.getBoard());
        }
    }

    //public abstract Coordinates shoot();
    public abstract Ship createShip(ShipType shipType, Coordinates coords, Orientation orient);

    public void createShips(List<Ship> shipsPlayer) { // TODO NO NEED TO THIS METHOD ANYMORE
        for (ShipType shipType : ShipType.values()) {
            //Ship ship = createShip(shipType);
            //shipsPlayer.add(ship);
            System.out.println("//===========" + playerId + " Board===========\\\\");
            printBoard(board);
        }
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public Board getMemory() {
        return memory;
    }

    public boolean amICurrentPlayer() {
        return amICurrentPlayer;
    }

    public void setAmICurrentPlayer(boolean amICurrentPlayer) {
        this.amICurrentPlayer = amICurrentPlayer;
    }

    public boolean addShip(ShipType shipType, Coordinates coordinates, Orientation orientation) {
        int col = coordinates.getCol();
        int row = coordinates.getRow();

        Ship ship = new Ship(new ArrayList<>(), shipType);

        //System.out.println(getPlayerId() + " places ship");
        System.out.println("You are going to place the ship: " + shipType);
        System.out.println("It has a length of " + shipType.getSize());

        if(board.getCell(new Coordinates(col,row)).getCellStatus()==CellStatus.SHIP){
            System.out.println("The ship collides with another ship or is out of the board! Try again.");
            return false;
        }


        int sizeShip = ship.getShipType().getSize();

        List<Cell> cellsToAdd = new ArrayList<>();
        cellsToAdd.add(board.getCell(new Coordinates(col,row)));

        int[][] orientationChanges = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int[] changes = orientationChanges[orientation.ordinal()];

        for (int i = 1; i < sizeShip; i++) {
            col += changes[0];
            row += changes[1];

            if (!isInsideBoard(col, row) || board.getCell(new Coordinates(col,row)).getCellStatus() == CellStatus.SHIP) {
                System.out.println("The ship collides with another ship or is out of the board! Try again.");
                return false;
            }

            cellsToAdd.add(board.getCell(new Coordinates(col,row)));
        }

        for (Cell cellToAdd : cellsToAdd) {
            cellToAdd.setCellStatus(CellStatus.SHIP);
            ship.add(cellToAdd);
        }
        remainingShips.add(ship);
        return true;
    }

    private boolean isInsideBoard(int col, int row) {
        return col >= 0 && col < board.getSizeRow() && row >= 0 && row < board.getSizeCol();
    }

}
