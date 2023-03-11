package battleship.game;
import java.util.List;

import static battleship.game.Display.printBoard;


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



     public static void handleShot(int col , int row ,Player otherPlayer){
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
             otherPlayer.getBoard().getCell(col,row).setCellStatus(CellStatus.HIT);
             System.out.println("HIT !");
             printBoard(otherPlayer.getBoard());
         }
     }

}

// remainingShips to handle
