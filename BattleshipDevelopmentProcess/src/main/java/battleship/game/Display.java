package battleship.game;

public class Display {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static void printMenu() {
        System.out.println("Battleship is starting ........");
        System.out.println("\n" + "                  ~.\n" +
                "           Ya...___|__..aab     .   .\n" +
                "            Y88a  Y88o  Y88a   (     )\n" +
                "             Y88b  Y88b  Y88b   `.oo'\n" +
                "             :888  :888  :888  ( (`-'\n" +
                "    .---.    d88P  d88P  d88P   `.`.\n" +
                "   / .-._)  d8P'\"\"\"|\"\"\"'-Y8P      `.`.\n" +
                "  ( (`._) .-.  .-. |.-.  .-.  .-.   ) )\n" +
                "   \\ `---( O )( O )( O )( O )( O )-' /\n" +
                "    `.    `-'  `-'  `-'  `-'  `-'  .' CJ\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printMessages(String message) {
        System.out.println(message);
    }

    public static void printMainMenuOptions() {
        System.out.println("Press:\n" +
                "\t 0 - Play\n" +
                "\t 1 - Print game rules\n" +
                "\t 2 - Exit game\n");
    }

    public static void printExitMessage() {
        System.out.println("Seen you soon sailor !\n");
    }

    public static void gameRules() {
        System.out.println("You and your opponent sit facing each other and neighter can see the other's ocean grid.\n" +
                "Place your fleet of 5 ships on the ocean grid.\n\n" +
                "Rules for placing ships:\n" +
                "Place each ship in any horizontal or vertical position but not diagonally.\n" +
                "Don't place a ship so that any part of it overlaps letters, numbers, the edge of the grid or another ship.\n" +
                "Don't change the position of any ships once the game has begun.\n\n\n" +
                "You and your opponent will alternate turns, calling out one shot per turn to try to hit each other's ships.\n" +
                "On your turn, pick a target hole and call out its location by letter and number.\n\n" +
                " ---> It's a hit!!\n" +
                "           If you call out a shot location that is occupied by a ship on your opponent's ocean grid, your shot is a hit!\n" +
                " ---> It's a miss...\n" +
                "           If you call out a shot location not occupied by a ship on your opponent's ocean grid, its a miss.\n\n" +
                "Once all the holes in any one ship are hit, the ship will sink. The owner of that ship must announce which ship was sunk.\n\n");
    }

    public static void printBoard(Board board) {
        System.out.print("   ");
        for (int col = 0; col < board.getSizeRow(); col++) {
            if (col < 10) {
                System.out.print(col + "  ");
            } else {
                System.out.print(col + " ");
            }
        }
        System.out.println();
        for (int row = 0; row < board.getSizeCol(); row++) {
            if (row < 10) {
                System.out.print(row + "  ");
            } else {
                System.out.print(row + " ");
            }


            for (int coll = 0; coll < board.getSizeRow(); coll++) {
                switch (board.getCell(new Coordinates(coll, row)).getCellStatus()) {
                    case OCEAN:
                        System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_RESET + " ");
                        break;
                    case HIT:
                        System.out.print(ANSI_RED_BACKGROUND + "  " + ANSI_RESET + " ");
                        break;
                    case SHIP:
                        System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_RESET + " ");
                        break;
                    case MISSED:
                        System.out.print(ANSI_BLACK_BACKGROUND + "  " + ANSI_RESET + " ");
                        break;

                }
            }

            System.out.println();
        }
    }


}

