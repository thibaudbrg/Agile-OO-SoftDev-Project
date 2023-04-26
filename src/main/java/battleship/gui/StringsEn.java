package battleship.gui;

public class StringsEn {

    private StringsEn() {
    }

    // Button labels
    public static final String START_BUTTON = "Start";
    public static final String RULES_BUTTON = "Rules";
    public static final String EXIT_BUTTON = "Exit";
    public static final String DIFFICULTY_LABEL = "CHOOSE YOUR DIFFICULTY";
    public static final String MULTIPLAYER_BUTTON_TIMER = "Multi & Timer";
    public static final String MULTIPLAYER_BUTTON_NO_TIMER = "Multi";
    public static final String EASY_BUTTON = "Easy Bot";
    public static final String HARD_BUTTON = "Hard Bot";

    // Window titles
    public static final String RULES = "\n" +
            "In this version of the Battleship game, there are some additional features\n " +
            "and game modes that provide an exciting and challenging experience.\n" +
            "\n" +
            "Rocks: There are rocks randomly distributed across the boards. If a player\n" +
            "clicks on a rock while attacking, the other player gains a \"bomb\" bonus for\n" +
            "their next turn. As a result, when the player clicks to attack, instead of \n" +
            "revealing just one cell, nine cells (a 3x3 square) are revealed. This provides\n" +
            "a bonus for the other player, allowing them to uncover potential ship locations more quickly.\n" +
            "\n" +
            "Ship orientation: To place a ship on the board, first click on one of the arrow keys\n" +
            "(left, right, down, or up) to set the orientation of the ship. Then, click on\n" +
            "the desired cell to place the ship with the chosen orientation.\n" +
            "\n" +
            "There are four game modes in this version:\n" +
            "Multiplayer: This is a classic two-player mode where players take turns attacking\n" +
            "each other's ships.\n" +
            "Blitz Multiplayer: This mode is similar to the regular multiplayer mode, but each player\n" +
            "has only 5 seconds to make a move. If a player doesn't make a move within the allotted time,\n" +
            "their turn is skipped, and the other player gets to play.\n" +
            "Easy Bot: In this solo mode, the player competes against an AI opponent with an easy\n" +
            "difficulty level.\n" +
            "Hard Bot: This solo mode is similar to the Easy Bot mode, but the AI opponent has a higher\n" +
            "difficulty level, making it more challenging to defeat.\n" +
            "\n" +
            "These additional features and game modes enhance the gameplay, making it more engaging\n" +
            "and strategic for both new and experienced players!!";


    // Prompts

    public static final String WHO_AM_I = "You are the %s.";

    public static final String CHOOSE_SHIP_PLACEMENTS =
            "You need to place your ships on the board.";

    public static final String CHOOSE_FIRST_SHIP_PLACEMENT =
            "You need to place the ship: %s. It has a length of %s.";

    public static final String CHOOSE_SPECIFIC_SHIP_PLACEMENT =
            "You need to place the ship: %s. It has a length of %s.";

    public static final String PLACEMENT_COLLISION =
            "The ship crashes or goes overboard! try again !";
    // "The ship collides with another ship or a rock or is out of the board! Try again.";


    public static final String GOOD_PLACEMENT =
            "Good placement!";

    // Information about the course of the game

    public static final String WILL_PLAY_FIRST =
            "%s will play first.";
    public static final String PLACED_SHIPS =
            "You have placed your ships.";
    public static final String CAN_PLAY =
            "It's your turn.";

    public static final String TIME_IS_UP =
            "Time's Up! It's not your turn anymore...";

    public static final String You_HAVE_A_BOMB =
            "you have a Bomb";

    public static final String HIT =
            "Hit!";

    public static final String ROCK_HIT = "You Hit a rock! the next Turn of your opponent will be a Bomb !";
    public static final String ALREADY_HIT = "Already hit!";

    public static final String MISS = "Miss...";

    public static final String ALREADY_MISSED = "Already missed...";

    public static final String SANK_SHIP =
            "You sank the %s. Still %s ship(s) remaining.";
    public static final String WINS =
            "%s wins the game!";


}