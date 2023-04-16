package battleship.game;

import battleship.gui.GameInfo;
import battleship.gui.Info;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class Player {
    private final PlayerId playerId;
    private final List<Ship> remainingShips;
    private final Board board;
    private final Board memory;
    private boolean amICurrentPlayer;
    private final GameInfo gameInfo;

    public Player(List<Ship> remainingShips, Board board, PlayerId playerId) {
        this.remainingShips = remainingShips;
        this.board = board;
        this.playerId = playerId;
        this.memory = new Board(board.getSizeCol(), board.getSizeRow());
        this.gameInfo = new GameInfo(new Info(playerId).whoAmI());
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
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public boolean handleShot(Coordinates coords, Player otherRealPlayer) {
        CellStatus newStatus = CellStatus.OCEAN;
        if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.HIT) {
            newStatus = CellStatus.HIT;
            //missMediaPlayer.setAutoPlay(true);
            //missMediaPlayer.play();
            gameInfo.addInfo(new Info(playerId).alreadyHit());
            return true;
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.OCEAN) {
            //missMediaPlayer.setAutoPlay(true);
            //missMediaPlayer.play();
            newStatus = CellStatus.MISSED;
            gameInfo.addInfo(new Info(playerId).miss());
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.MISSED);
            return true;
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.MISSED) {
            newStatus = CellStatus.MISSED;
            //missMediaPlayer.setAutoPlay(true);
            //missMediaPlayer.play();
            gameInfo.addInfo(new Info(playerId).alreadyMissed());
            return true;
        } else {
            //hitMediaPlayer.setAutoPlay(true);
            //hitMediaPlayer.play();
            newStatus = CellStatus.HIT;
            gameInfo.addInfo(new Info(playerId).hit());
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.HIT);
            Iterator<Ship> iterator = otherRealPlayer.remainingShips.iterator();
            while (iterator.hasNext()) {
                Ship ship = iterator.next();
                if (ship.hasSunk()) {
                    iterator.remove();
                    gameInfo.addInfo(new Info(playerId).sankShip(ship.getShipType(), otherRealPlayer.getNumberOfRemainingShips()));
                }
            }
            memory.getCell(coords).setCellStatus(newStatus);
            return false;
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


        if (board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.SHIP) {
            gameInfo.addInfo(new Info(playerId).placementCollision());
            return false;
        }

        int sizeShip = ship.getShipType().getSize();

        List<Cell> cellsToAdd = new ArrayList<>();
        cellsToAdd.add(board.getCell(new Coordinates(col, row)));

        int[][] orientationChanges = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        int[] changes = orientationChanges[orientation.ordinal()];

        for (int i = 1; i < sizeShip; i++) {
            col += changes[0];
            row += changes[1];

            if (!isInsideBoard(col, row) || board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.SHIP) {
                gameInfo.addInfo(new Info(playerId).placementCollision());
                return false;
            }

            cellsToAdd.add(board.getCell(new Coordinates(col, row)));
        }

        for (Cell cellToAdd : cellsToAdd) {
            cellToAdd.setCellStatus(CellStatus.SHIP);
            ship.add(cellToAdd);
        }

        gameInfo.addInfo(new Info(playerId).goodPlacement());
        if (shipType != ShipType.DESTROYER) {
            gameInfo.addInfo(new Info(playerId).chooseSpecificShipPlacement(shipType.next()));
        }

        remainingShips.add(ship);
        return true;
    }

    private boolean isInsideBoard(int col, int row) {
        return col >= 0 && col < board.getSizeRow() && row >= 0 && row < board.getSizeCol();
    }


}
