package battleship.game;

import battleship.gui.GameMode;
import battleship.gui.GraphicalGame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static battleship.game.Display.*;

public class Game {


    static final Scanner scanner = new Scanner(System.in);
    private Game() {
    }

    public static void play(GameMode gameMode, int rows , int cols) {


        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();

        List<Board> boards = generateBoards(rows,cols);

        List<Player> players = generatePlayers(gameMode, shipsPlayer1, shipsPlayer2, boards);
        //Player player1 = players.get(0);
        //Player player2 = players.get(1);

        GraphicalGame.initial(gameMode, players);


        /*
        player1.createShips(shipsPlayer1);
        player2.createShips(shipsPlayer2);

        System.out.println("//===========" + PlayerId.PLAYER_1 + " Board===========\\\\");
        printBoard(player1.getBoard());
        System.out.println("----------------------------------------");
        System.out.println("//===========" + PlayerId.PLAYER_2 + " Board===========\\\\");
        printBoard(player2.getBoard());

        Player currentPlayer = player1;

        while (true) {
            Player otherPlayer = currentPlayer == player2 ? player1 : player2;
            Coordinates shootCoords = currentPlayer.shoot();
            currentPlayer.handleShot(shootCoords, otherPlayer);

            if (otherPlayer.getRemainingShips().isEmpty()) {
                printBoard(otherPlayer.getBoard());

                System.out.println(currentPlayer.getPlayerId() + " Wins!");
                break;
            }
            currentPlayer = currentPlayer == player2 ? player1 : player2;
        }

         */

    }

    private static List<Board> generateBoards(int rows, int cols) {
        List<Board> boards = new ArrayList<>();

        /*int sizeCol, sizeRow;

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
        scanner.nextLine();*/


        Board board1 = new Board(cols, rows);
        Board board2 = new Board(cols, rows);
        boards.add(board1);
        boards.add(board2);
        return boards;
    }

    private static List<Player> generatePlayers(GameMode gameMode, List<Ship> ships1, List<Ship> ships2, List<Board> boards) {
        Player player1 = null;
        Player player2 = null;
        Board board1 = boards.get(0);
        Board board2 = boards.get(1);

        switch (gameMode) {
            case MULTIPLAYER:
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new RealPlayer(ships2, board2, PlayerId.PLAYER_2);
                break;
            case EASY, HARD:
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new AIPlayer(ships2, board2, PlayerId.PLAYER_2);
                break;
        }

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        /*List<Player> players = new ArrayList<>();
        System.out.println("Select the mode: 1. SOLO, 2. MULTIPLAYER ");

        int mode;
        do {
            mode = scanner.nextInt();
            if (mode != 1 && mode != 2) {
                System.out.println("ERROR: You need to enter either 1 or 2");
            }
        } while (mode != 1 && mode != 2);

        Player player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
        Player player2 = (mode == 1) ? new AIPlayer(ships2, board2, PlayerId.PLAYER_2) : new RealPlayer(ships2, board2, PlayerId.PLAYER_2);

        players.add(player1);
        players.add(player2);

         */

        return players;
    }
}
