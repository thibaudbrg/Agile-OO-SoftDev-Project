package battleship.gui;

public class StringsEn {

    private StringsEn() {
    }

    // Button labels
    public static final String START_BUTTON = "Start";
    public static final String RULES_BUTTON = "Rules";
    public static final String EXIT_BUTTON = "Exit";
    public static final String DIFFICULTY_LABEL = "CHOOSE YOUR DIFFICULTY";
    public static final String MULTIPLAYER_BUTTON = "Multiplayer";
    public static final String EASY_BUTTON = "Easy Bot";
    public static final String HARD_BUTTON = "Hard Bot";

    // Window titles
    public static final String RULES = "\n" +
            "You and your opponent sit facing each other and neighter can see the other's ocean grid.\n" +
            "Place your fleet of 5 ships on the ocean grid.\n\n" +
            "Rules for placing ships:\n" +
            "Place each ship in any horizontal or vertical position but not diagonally.\n" +
            "Don't place a ship so that any part of it overlaps letters, numbers, the edge of the grid\n" +
            "or another ship.\n" +
            "Don't change the position of any ships once the game has begun.\n\n" +
            "You and your opponent will alternate turns, calling out one shot per turn to try to hit\n" +
            "each other's ships.\n" +
            "On your turn, pick a target hole and call out its location by letter and number.\n\n" +
            " ---> It's a hit!!\n" +
            "      If you call out a shot location occupied by a ship on your opponent's ocean grid,\n" +
            "      your shot is a hit!\n" +
            " ---> It's a miss...\n" +
            "      If you call out a shot location not occupied by a ship on your opponent's ocean grid,\n" +
            "      its a miss.\n\n" +
            "Once all the holes in any one ship are hit, the ship will sink. The owner of that ship\n" +
            "must announce which ship was sunk.\n\n";


    // Prompts

    public static final String WHO_AM_I = "You are the %s.";

    public static final String CHOOSE_SHIP_PLACEMENTS =
            "You need to place your ships on the board.";

    public static final String CHOOSE_FIRST_SHIP_PLACEMENT =
            "You need to place the ship: %s. It has a length of %s.";

    public static final String CHOOSE_SPECIFIC_SHIP_PLACEMENT =
            "You need to place the ship: %s. It has a length of %s.";

    public static final String PLACEMENT_COLLISION =
            "The ship collides with another ship or is out of the board! Try again.";

    public static final String GOOD_PLACEMENT =
            "Good placement!";

    // Information about the course of the game

    public static final String WILL_PLAY_FIRST =
            "%s will play first.";
    public static final String PLACED_SHIPS =
            "You have placed your ships.";
    public static final String CAN_PLAY =
            "It's your turn.";

    public static final String HIT =
            "Hit!";

    public static final String ALREADY_HIT = "Already hit!";

    public static final String MISS = "Miss...";

    public static final String ALREADY_MISSED = "Already missed...";

    public static final String SANK_SHIP =
            "You sank the %s. Still %s ship(s) remaining.";
    public static final String WINS =
            "%s wins the game!";


}
