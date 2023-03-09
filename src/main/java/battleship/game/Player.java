package battleship.game;
import java.util.List;


public class Player {
    private List<Ship> remainingShips;
    private Board board;

    public Player(List<Ship> ships, Board board) {
        this.remainingShips = ships;
        this.board = board;
    }

    public List<Ship> getShips() {
        return remainingShips;
    }



    public Board getBoard() {
        return board;
    }

    public int getNumberOfRemainingShips() {
        return remainingShips.size();
    }

    public int numberOfCells0fShips() {
        int num = 0 ;
        for (int i = 0; i < board.getSizeCol(); i++) {
            for (int j = 0; j < board.getSizeRow(); j++) {
                if(board.getCell(i,j).getCellStatus().equals(CellStatus.SHIP)){
                    num++;
                }
            }
        }
        return num;
    }

     public boolean handleShot(int col , int row ,Player otherPlayer){
         if(otherPlayer.getBoard().getCell(col,row).getCellStatus()==CellStatus.HIT){
             System.out.println("already Hit");
             return false;
         } else if (otherPlayer.getBoard().getCell(col,row).getCellStatus()==CellStatus.OCEAN) {
             System.out.println("miss !");
             otherPlayer.getBoard().getCell(col,row).setCellStatus(CellStatus.MISSED);
             return false;
         } else if (otherPlayer.getBoard().getCell(col,row).getCellStatus()==CellStatus.MISSED) {
             System.out.println("already missed !");
             return false;
         } else {
             otherPlayer.getBoard().getCell(col,row).setCellStatus(CellStatus.HIT);
             System.out.println("HIT !");
             return true;
         }
     }

}

// remainingShips to handle
