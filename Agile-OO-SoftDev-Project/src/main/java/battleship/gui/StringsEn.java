package battleship.gui;

public class StringsEn {

    private StringsEn() {
    }

    // Card names
    public static final String CARRIER = "Carrier";
    public static final String BATTLESHIP = "Battleship";
    public static final String CRUISER = "Cruiser";
    public static final String SUBMARINE = "Submarine";
    public static final String DESTROYER = "Destroyer";

    // Button labels
    public static final String START_BUTTON = "Start";
    public static final String RULES_BUTTON = "Rules";
    public static final String EXIT_BUTTON = "Exit";
    public static final String MULTIPLAYER_BUTTON = "Multiplayer";
    public static final String EASY_BUTTON = "Easy";
    public static final String HARD_BUTTON = "Hard";

    // Window titles
    public static final String RULES = "You and your opponent sit facing each other and neighter can see the other's ocean grid.\n" +
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
            "Once all the holes in any one ship are hit, the ship will sink. The owner of that ship must announce which ship was sunk.\n\n";


    // Prompts
    public static final String CHOOSE_SHIP_PLACEMENTS =
            "%s, you need to place your ships on the board";


    // Information about the course of the game
    public static final String WILL_PLAY_FIRST =
            "%s will play first.\n\n";
    public static final String PLACED_SHIPS =
            "%s has placed his ships.\n";
    public static final String CAN_PLAY =
            "\nIt's %s's turn.\n";
    public static final String HIT =
            "%s hit!\n";
    public static final String MISS =
            "%s missed...\n";
    public static final String SANK_SHIP =
            "%s sank the %s.\n";
    public static final String WINS =
            "\n%s won the game!\n";


}
