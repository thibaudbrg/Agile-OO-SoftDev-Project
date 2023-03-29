package battleship.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static battleship.game.Display.printBoard;
import static battleship.game.Game.scanner;

public class RealPlayer extends Player {
    private static final Scanner scanner = new Scanner(System.in);

    public RealPlayer(List<Ship> remainingShips, Board board , PlayerId playerId) {
        super(remainingShips, board,playerId);
    }
    public  Coordinates shoot() {
        System.out.println(getPlayerId() + " shoots");

        System.out.println("select col: ");
        int col = scanner.nextInt();

        System.out.println("select row: ");
        int row = scanner.nextInt();

        return new Coordinates(col, row);
    }



    public Ship createShip(ShipType shipType) {
        System.out.println(getPlayerId() + " places ship");
        System.out.println("You are going to place the ship: " + shipType);
        System.out.println("It has a length of " + shipType.getLabel());

        Ship ship = new Ship(new ArrayList<>(), shipType);
        int col, row;
        boolean result;
        do {
            do {
                System.out.println("Select a column: ");
                col = scanner.nextInt();
                if (col < 0 || getBoard().getSizeCol() <= col) {
                    System.out.println("The size of the board is: n°Col = " + getBoard().getSizeCol() + " n°Row = " + getBoard().getSizeRow());
                }
            } while (col < 0 || getBoard().getSizeCol() <= col);
            scanner.nextLine();

            do {
                System.out.println("Select a row: ");
                row = scanner.nextInt();
                if (row < 0 || getBoard().getSizeRow() <= row) {
                    System.out.println("The size of the board is: n°Col = " + getBoard().getSizeCol() + " n°Row = " + getBoard().getSizeRow());
                }
            } while (row < 0 || getBoard().getSizeRow() <= row);
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
            result = getBoard().addShip(coords, ship, Orientation.values()[orientation - 1]);

        } while (!result);

        System.out.println("Good placement!");
        return ship;
    }


}

