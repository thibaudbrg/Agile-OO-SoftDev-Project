package battleship.game;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static battleship.game.Display.*;
import static battleship.game.Player.*;

public class Game {
    public static Scanner scanner = new Scanner(System.in);
    Map<PlayerId, Player> playerMap = new HashMap<>();


    public Game(){
        gameLogic();
    }

    public void gameLogic() {
        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();
        List<Board> boards = generateBoards();


       createShips(0,shipsPlayer1,boards);
       createShips(1,shipsPlayer2,boards);


        Player player1 = new Player(shipsPlayer1, boards.get(0));
        Player player2 = new Player(shipsPlayer2, boards.get(1));
        playerMap.put(PlayerId.PLAYER_1,player1);
        playerMap.put(PlayerId.PLAYER_2,player2);

        System.out.println("//===========Player 1 Board===========\\\\");
        printBoard(boards.get(0));
        System.out.println("----------------------------------------");
        System.out.println("//===========Player2 Board============\\\\");
        printBoard(boards.get(1));

        int next = 0;

        while (true) {
            int[] ShootCoordinates;
            ShootCoordinates = shoot(next);
            PlayerId thisPlayerID = next == 0 ? PlayerId.PLAYER_1 : PlayerId.PLAYER_2;
            PlayerId theOtherPlayerID = next == 0 ? PlayerId.PLAYER_2 : PlayerId.PLAYER_1;

            handleShot(ShootCoordinates[0], ShootCoordinates[1],playerMap.get(theOtherPlayerID));

            if (playerMap.get(theOtherPlayerID).numberOfCells0fShips() == 0) {
                printBoard(playerMap.get(theOtherPlayerID).getBoard());
                System.out.println(thisPlayerID + " wins!");
                break;
            }

            next = (next == 0 ? 1 : 0);
        }
    }

    public int getIntegerMenuOption() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    public List<Board> generateBoards() {
        List<Board> boards = new ArrayList<>();
        int col, row;

        System.out.println("Select the number of columns of the board: ");
        do {
            col = scanner.nextInt();
            if(col < 0 || 25 < col) {
                System.out.println("ERROR: You need to enter a value between 0 and 25");
            }
        } while(col < 0 || 25 < col);
        scanner.nextLine();


        System.out.println("Select the number of lines of the board: ");
        do {
            row = scanner.nextInt();
            if(row < 0 || 25 < row) {
                System.out.println("ERROR: You need to enter a value between 0 and 25");
            }
        } while(row < 0 || 25 < row);


        Board board1 = new Board(col, row);
        Board board2 = new Board(col, row);
        boards.add(board1);
        boards.add(board2);
        return boards;
    }

    public void createShips(int j, List<Ship> shipsPlayer, List<Board> boards){
        for (int i = 0; i < 5; ++i) {
            Ship one = createShip(j,boards,i);
            shipsPlayer.add(one);
            int k = j+1;
            System.out.println("//===========Player "+ k  +" Board===========\\\\");
            printBoard(boards.get(j));
        }

    }
    public Ship createShip(int player, List<Board> boards,int shipType) {
        int GamePlayer = player +1;
        Cell shipPart;
        List<Integer> shipInfos;
        Ship ship;
        System.out.println("Player " + GamePlayer + " place ship");
        boolean worked = false;
        do {
            shipInfos = askShipInfos(boards,shipType);
            ship = new Ship(new ArrayList<>(), ShipType.values()[shipInfos.get(2)]);
            shipPart = new Cell(shipInfos.get(0), shipInfos.get(1), CellStatus.SHIP);
            worked = boards.get(player).addShip(shipPart, ship, Orientation.values()[shipInfos.get(3)-1]);
            //if (!worked) System.out.println("The ship is out of the board! Try again.");
        } while (!worked);
        System.out.println("Good placement!");

        return ship;
    }
    private List<Integer> askShipInfos(List<Board> boards,int shipType) {
        int col, row;
        System.out.println("now you are going to place the ship: "+ ShipType.values()[shipType]);
        System.out.println("it has a length of " + ShipType.values()[shipType].label);

        do {
            System.out.println("Select a column: ");
            col = scanner.nextInt();
            if(col < 0 || boards.get(0).getSizeCol() <= col) {
                System.out.println("The size of the board is: n°Col = " + boards.get(0).getSizeCol() + " n°Row = " + boards.get(0).getSizeRow());
            }
        } while (col < 0 || boards.get(0).getSizeCol() <= col);
        scanner.nextLine();

        do {
            System.out.println("Select a row: ");
            row = scanner.nextInt();
            if(row < 0 || boards.get(0).getSizeRow() <= row) {
                System.out.println("The size of the board is: n°Col = " + boards.get(0).getSizeCol() + " n°Row = " + boards.get(0).getSizeRow());
            }
        } while (row < 0 || boards.get(0).getSizeRow() <= row);
        scanner.nextLine();

        /*
        int shipType;
        do {
            System.out.println("Select ship: \n" + "1. CARRIER \n" + "2. BATTLESHIP \n" + "3. CRUISER \n" + "4. SUBMARINE \n" + "5. DESTROYER \n");
            shipType = scanner.nextInt();
            if (shipType < 1 || ShipType.values().length < shipType) {
                System.out.println("There are " + ShipType.values().length + " available");
            }
        } while (shipType < 1 || ShipType.values().length < shipType);
        scanner.nextLine();
        */

        int orientation;
        do {
            System.out.println("select orientation: \n" + "1. NORTH \n" + "2. SOUTH \n" + "3. EAST \n" + "4. WEST \n");
            orientation = scanner.nextInt();
            if (orientation < 1 || Orientation.values().length < orientation) {
                System.out.println("There are " + Orientation.values().length + " available");
            }
        } while(orientation < 1 || Orientation.values().length < orientation);


        List<Integer> shipInfos = new ArrayList<>();
        shipInfos.add(col);
        shipInfos.add(row);
        shipInfos.add(shipType);
        shipInfos.add(orientation);
        return shipInfos;
    }



    public int[] shoot(int player) {
        int GamePlayer = player + 1;
        System.out.println("Player " + GamePlayer + " shoot");
        System.out.println("select row: ");
        int row = scanner.nextInt();
        System.out.println("select col: ");
        int col = scanner.nextInt();
        return new int[]{row, col};
    }


}
