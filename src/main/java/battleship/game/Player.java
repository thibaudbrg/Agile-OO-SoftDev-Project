package battleship.game;
import java.util.List;


public class Player {
    private List<Ship> ships;
    private Board board;
    private int remainingShips = 0 ;

    public Player(List<Ship> ships, Board board) {
        this.ships = ships;
        this.board = board;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public Board getBoard() {
        return board;
    }

    public int getRemainingShips() {
        return remainingShips;
    }

    public int numberOfCells0fShips(List<Ship> ships) {
        int sumOfAllCells = 0;
        for (Ship ship : ships) {
            sumOfAllCells += ship.getShipType().label;
    }
        return sumOfAllCells;
}
    public boolean handleShot(int x , int y){
        for (Ship ship : ships){
            for (Cell cell : ship.getFields()) {
                if (cell.getRow() == y && cell.getCol() == x && cell.getCellStatus().equals(CellStatus.SHIP)){
                    cell.setCellStatus(CellStatus.HIT);
                    board.getCell(x,y).setCellStatus(CellStatus.HIT);
                    return true;
                } else if (cell.getRow() == y && cell.getCol() == x && cell.getCellStatus().equals(CellStatus.HIT)){
                    cell.setCellStatus(CellStatus.HIT);
                    System.out.println("Already Hit");
                    return false;
                }
            }
        }

        board.getCell(x,y).setCellStatus(CellStatus.MISSED);
        System.out.println("Miss!");
        return false;
    }

}

