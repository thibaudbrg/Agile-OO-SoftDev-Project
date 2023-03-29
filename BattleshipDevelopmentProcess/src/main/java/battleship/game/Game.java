package battleship.game;

import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static battleship.game.Display.*;

public class Game {
    static final Scanner scanner = new Scanner(System.in);

    private Game() {
    }

    public static void play() {
        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();

        List<Board> boards = generateBoards();
        Board board1 = boards.get(0);
        Board board2 = boards.get(1);


        RealPlayer player1  = new RealPlayer(shipsPlayer1, board1,PlayerId.PLAYER_1);
        RealPlayer player2 = new RealPlayer(shipsPlayer2, board2, PlayerId.PLAYER_2);
        player1.createShips(shipsPlayer1);
        player2.createShips(shipsPlayer2);

        System.out.println("//===========" + PlayerId.PLAYER_1 + " Board===========\\\\");
        printBoard(player1.getBoard());
        System.out.println("----------------------------------------");
        System.out.println("//===========" + PlayerId.PLAYER_2 + " Board===========\\\\");
        printBoard(player2.getBoard());

        Player currentPlayer = player1;

        while (true) {
            Player otherPlayer = currentPlayer == player2 ? player1 : player2 ;
            Coordinates shootCoords = currentPlayer.shoot();
            currentPlayer.handleShot(shootCoords,otherPlayer);

            if (otherPlayer.getRemainingShips().isEmpty()) {
                printBoard(otherPlayer.getBoard());

                System.out.println(currentPlayer.getPlayerId() + " Wins!");
                break;
            }
            currentPlayer = currentPlayer == player2 ? player1 : player2 ;
        }
    }

    private static List<Board> generateBoards() {
        List<Board> boards = new ArrayList<>();
        int sizeCol, sizeRow;

        System.out.println("Select the number of columns of the board: ");
        do {
            sizeCol = scanner.nextInt();
            if (sizeCol < 0 || 25 < sizeCol) {
                System.out.println("ERROR: You need to enter a value between 0 and 25");
            }
        } while (sizeCol < 0 || 25 < sizeCol);
        scanner.nextLine();


        System.out.println("Select the number of lines of the board: ");
        do {
            sizeRow = scanner.nextInt();
            if (sizeRow < 0 || 25 < sizeRow) {
                System.out.println("ERROR: You need to enter a value between 0 and 25");
            }
        } while (sizeRow < 0 || 25 < sizeRow);

        Board board1 = new Board(sizeCol, sizeRow);
        Board board2 = new Board(sizeCol, sizeRow);
        boards.add(board1);
        boards.add(board2);
        return boards;
    }





}
