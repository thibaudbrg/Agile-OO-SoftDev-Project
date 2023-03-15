package battleship.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Ship> shipsPlayer1 = new ArrayList<>();
    private final List<Ship> shipsPlayer2 = new ArrayList<>();
    List<Board> boards;

    public void gameLogic() {
        Input input = new Input();
        boards = input.getBoards();
        Board boardPlayer1 = boards.get(0);
        Board boardPlayer2 = boards.get(1);
        Display display = new Display();

        for (int i = 0; i < 5; ++i) {
            Ship one = input.createShip(0);
            shipsPlayer1.add(one);
            System.out.println("//===========Player 1 Board===========\\\\");
            display.printBoard(boardPlayer1);

        }

        for (int i = 0; i < 5; ++i) {
            Ship one = input.createShip(1);
            shipsPlayer2.add(one);
            System.out.println("//===========Player 2 Board===========\\\\");
            display.printBoard(boardPlayer2);

        }

        Player player1 = new Player(shipsPlayer1, boardPlayer1);
        Player player2 = new Player(shipsPlayer1, boardPlayer1);

        System.out.println("//===========Player 1 Board===========\\\\");
        display.printBoard(boardPlayer1);
        System.out.println("----------------------------------------");
        System.out.println("//===========Player2 Board============\\\\");
        display.printBoard(boardPlayer2);

        int numberOfShipsPlayer1 = player1.numberOfCells0fShips(shipsPlayer1);
        int numberOfShipsPlayer2 = player1.numberOfCells0fShips(shipsPlayer2);

        while (true) {
            int[] ShootCoordinates;
            ShootCoordinates = input.shoot(0);

            if (player2.handleShot(ShootCoordinates[0], ShootCoordinates[1])) {
                display.printBoard(player2.getBoard());
                --numberOfShipsPlayer2;
            } else {
                display.printBoard(player2.getBoard());
            }

            if (numberOfShipsPlayer2 == 0) {
                display.printBoard(player2.getBoard());
                System.out.println("Player 1 wins!");
                break;
            }

            if (player1.handleShot(ShootCoordinates[0], ShootCoordinates[1])) {
                display.printBoard(player1.getBoard());
                --numberOfShipsPlayer1;
            } else {
                display.printBoard(player1.getBoard());
            }

            if (numberOfShipsPlayer1 == 0) {
                display.printBoard(player1.getBoard());
                System.out.println("Player 2 wins!");
                break;
            }
        }
    }
}
