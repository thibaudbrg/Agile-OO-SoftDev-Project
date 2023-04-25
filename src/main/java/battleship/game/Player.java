package battleship.game;

import battleship.gui.GameInfo;
import battleship.gui.Info;
import battleship.gui.PlayerObserver;


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
    private final List<PlayerObserver> observers = new ArrayList<>();
    private boolean hasBomb ;



    public Player(List<Ship> remainingShips, Board board, PlayerId playerId) {
        this.remainingShips = remainingShips;
        this.board = board;
        this.playerId = playerId;
        this.memory = new Board(board.getNumRow(), board.getNumCol(),false);
        this.gameInfo = new GameInfo(new Info(playerId).whoAmI());
        this.hasBomb = false;
    }

    public void addObserver(PlayerObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PlayerObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(int remainingShips) {
        for (PlayerObserver observer : observers) {
            observer.onRemainingShipsChanged(remainingShips);
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
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public CellStatus handleShot(Coordinates coords, Player otherRealPlayer) {
        CellStatus newStatus = CellStatus.OCEAN;
        if (this.hasBomb){
            this.setHasBomb(false);
            handleShot(coords,otherRealPlayer);
            List<Coordinates> neighbouringCoordinates = neighbouringCoordinates(coords);
            for (Coordinates neighbouringCoords :neighbouringCoordinates) {
                handleShot(neighbouringCoords,otherRealPlayer);
            }
           return CellStatus.HIT;
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.HIT
                || otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.ROCK_HIT) {
            gameInfo.addInfo(new Info(playerId).alreadyHit());
            return CellStatus.ALREADY_HIT;
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.OCEAN) {
            gameInfo.addInfo(new Info(playerId).miss());
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.MISSED);
            memory.getCell(coords).setCellStatus(CellStatus.MISSED);
            return CellStatus.MISSED;
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.MISSED) {
            gameInfo.addInfo(new Info(playerId).alreadyMissed());
            return CellStatus.ALREADY_MISSED;
        } else if (otherRealPlayer.getBoard().getCell(coords).getCellStatus() == CellStatus.ROCK) {
            gameInfo.addInfo(new Info(playerId).rockHit());
            otherRealPlayer.getBoard().getCell(coords).setCellStatus(CellStatus.ROCK_HIT);
            otherRealPlayer.getGameInfo().addInfo(new Info((otherRealPlayer.getPlayerId())).hasBomb());
            otherRealPlayer.setHasBomb(true);
            return CellStatus.ROCK_HIT;
        }
            else{
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
            notifyObservers(otherRealPlayer.getNumberOfRemainingShips());
            memory.getCell(coords).setCellStatus(newStatus);
            return CellStatus.HIT;
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


        if (board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.SHIP || board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.ROCK ) {
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

            if (!isInsideBoard(col, row) ||
                    board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.SHIP ||
                    board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.ROCK) {
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
        return col >= 0 && col < board.getNumCol() && row >= 0 && row < board.getNumRow();
    }

    public boolean getHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public List<Coordinates> neighbouringCoordinates(Coordinates coords) {
        List<Coordinates> neighbours = new ArrayList<>();
        int row = coords.getRow();
        int col = coords.getCol();
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1},{0, 1}, {1, -1},{1, 0},{1, 1}};
        for (int[] offset : offsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];
            if (isInsideBoard(newCol,newRow)) {
                neighbours.add(new Coordinates(newCol, newRow));
            }
        }
        return neighbours;
    }

}
