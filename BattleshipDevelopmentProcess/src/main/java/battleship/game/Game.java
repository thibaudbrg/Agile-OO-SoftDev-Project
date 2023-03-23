package battleship.game;

import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static battleship.game.Display.*;

public class Game {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<PlayerId, Player> playerMap = new HashMap<>();

    private Game() {}

    public static void play() {
        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();

        List<Board> boards = generateBoards();
        Board board1 = boards.get(0);
        Board board2 = boards.get(1);

        createShips(PlayerId.PLAYER_1, shipsPlayer1, board1);
        createShips(PlayerId.PLAYER_2, shipsPlayer2, board2);


        Player player1 = new Player(shipsPlayer1, board1);
        Player player2 = new Player(shipsPlayer2, board2);

        playerMap.put(PlayerId.PLAYER_1, player1);
        playerMap.put(PlayerId.PLAYER_2, player2);

        System.out.println("//===========Player 1 Board===========\\\\");
        printBoard(board1);
        System.out.println("----------------------------------------");
        System.out.println("//===========Player2 Board============\\\\");
        printBoard(board2);

        PlayerId currentPlayerId = PlayerId.PLAYER_1;

        while (true) {
            Coordinates shootCoords = shoot(currentPlayerId);
            playerMap.get(currentPlayerId).handleShot(shootCoords, playerMap.get(currentPlayerId.next()));

            if (playerMap.get(currentPlayerId.next()).getRemainingShips().isEmpty()) {
                printBoard(playerMap.get(currentPlayerId.next()).getBoard());

                System.out.println(currentPlayerId + " Wins!");
                break;
            }
            currentPlayerId = currentPlayerId.next();
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

    private static void createShips(PlayerId playerId, List<Ship> shipsPlayer, Board board) {
        for (ShipType shipType : ShipType.values()) {
            Ship ship = createShip(playerId, board, shipType);
            shipsPlayer.add(ship);
            System.out.println("//===========Player " + playerId.ordinal() + 1 + " Board===========\\\\");
            printBoard(board);
        }

    }

    private static Ship createShip(PlayerId playerId, Board board, ShipType shipType) {
        System.out.println("Player " + playerId.ordinal() + 1 + " places ship");
        System.out.println("You are going to place the ship: " + shipType);
        System.out.println("It has a length of " + shipType.label);

        Ship ship = new Ship(new ArrayList<>(), shipType);
        int col, row;
        boolean result;
        do {
            do {
                System.out.println("Select a column: ");
                col = scanner.nextInt();
                if (col < 0 || board.getSizeCol() <= col) {
                    System.out.println("The size of the board is: n°Col = " + board.getSizeCol() + " n°Row = " + board.getSizeRow());
                }
            } while (col < 0 || board.getSizeCol() <= col);
            scanner.nextLine();

            do {
                System.out.println("Select a row: ");
                row = scanner.nextInt();
                if (row < 0 || board.getSizeRow() <= row) {
                    System.out.println("The size of the board is: n°Col = " + board.getSizeCol() + " n°Row = " + board.getSizeRow());
                }
            } while (row < 0 || board.getSizeRow() <= row);
            scanner.nextLine();

            int orientation;
            do {
                System.out.println("select orientation: \n" + "1. NORTH \n" + "2. SOUTH \n" + "3. EAST \n" + "4. WEST \n");
                orientation = scanner.nextInt();
                if (orientation < 1 || Orientation.values().length < orientation) {
                    System.out.println("There are " + Orientation.values().length + " available");
                }
            } while (orientation < 1 || Orientation.values().length < orientation);


            Coordinates coords = new Coordinates(col, row);
            Cell firstCell = board.getCell(coords);
            result = board.addShip(firstCell, ship, Orientation.values()[orientation - 1]);

        } while (!result);

        System.out.println("Good placement!");
        return ship;
    }

    private static Coordinates shoot(PlayerId playerId) {
        System.out.println("Player " + playerId.ordinal() + 1 + " shoots");

        System.out.println("select col: ");
        int col = scanner.nextInt();

        System.out.println("select row: ");
        int row = scanner.nextInt();

        return new Coordinates(col, row);
    }
}
