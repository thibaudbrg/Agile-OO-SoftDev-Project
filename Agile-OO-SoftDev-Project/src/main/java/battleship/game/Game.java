package battleship.game;

import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static battleship.game.Display.*;

public class Game {
    static final Scanner scanner = new Scanner(System.in);
    private static Player currentPlayer;
    private static Player player1;
    private static Player player2;
    private boolean over;

    private Game() {
    }

    public static void play() {
        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();

        List<Board> boards = generateBoards();
        Board board1 = boards.get(0);
        Board board2 = boards.get(1);

        List<Player> players = generatePlayers(shipsPlayer1, shipsPlayer2, board1, board2);
        player1 = players.get(0);
        player2 = players.get(1);

        player1.createShips(shipsPlayer1);
        player2.createShips(shipsPlayer2);

        System.out.println("//===========" + PlayerId.PLAYER_1 + " Board===========\\\\");
        printBoard(player1.getBoard());
        System.out.println("----------------------------------------");
        System.out.println("//===========" + PlayerId.PLAYER_2 + " Board===========\\\\");
        printBoard(player2.getBoard());

        currentPlayer = player1;

        while (true) {
            Player otherPlayer = currentPlayer == player2 ? player1 : player2 ;
            Coordinates shootCoords = currentPlayer.shoot();
            currentPlayer.handleShot(shootCoords,otherPlayer);
            currentPlayer.makeMove(shootCoords,player1,player2);

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
        scanner.nextLine();


        Board board1 = new Board(sizeCol, sizeRow);
        Board board2 = new Board(sizeCol, sizeRow);
        boards.add(board1);
        boards.add(board2);
        return boards;
    }

    private static List<Player> generatePlayers(List<Ship> ships1, List<Ship> ships2, Board board1, Board board2){
        List<Player> players = new ArrayList<>();
        System.out.println("Select the mode: 1. SOLO, 2. MULTIPLAYER ");

        int mode;
        do {
            mode = scanner.nextInt();
            if (mode != 1 && mode != 2) {
                System.out.println("ERROR: You need to enter either 1 or 2");
            }
        } while (mode != 1 && mode != 2);

        Player player1  = new RealPlayer(ships1, board1,PlayerId.PLAYER_1);
        Player player2;

        player2 = mode==1 ? new AIPlayer(ships2, board2, PlayerId.PLAYER_2) : new RealPlayer(ships2, board2, PlayerId.PLAYER_2);

        players.add(player1);
        players.add(player2);

        return players;
    }



    }


