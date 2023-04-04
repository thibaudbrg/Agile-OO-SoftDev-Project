package battleship.gui;

import battleship.game.ShipType;

/**
 * Generate most of the in-game messages
 */
public final class Info {
    private final String playerName;

    /**
     * Public constructor
     *
     * @param playerName name of the player
     */
    public Info(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns a strings with the card's name
     *
     * @param shipType (ShipType) given ShipType
     * @return (String) a strings with the ship name
     */
    public static String shipName(ShipType shipType) {
        switch (shipType) {
            case CARRIER:
                return StringsEn.CARRIER;
            case BATTLESHIP:
                return StringsEn.BATTLESHIP;
            case CRUISER:
                return StringsEn.CRUISER;
            case SUBMARINE:
                return StringsEn.SUBMARINE;
            case DESTROYER:
                return StringsEn.DESTROYER;
            default:
                throw new Error();
        }
    }


    /**
     * Returns a String declaring that the player needs to choose the ships placements
     *
     * @return (String) a String declaring that the player needs to choose the ships placements
     */
    public String chooseShipsPlacements() {
        return String.format(StringsEn.CHOOSE_SHIP_PLACEMENTS, playerName);
    }

    /**
     * Returns a String declaring which player plays first
     *
     * @return (String) a String declaring which player plays first
     */
    public String willPlayFirst() {
        return String.format(StringsEn.WILL_PLAY_FIRST, playerName);
    }


    /**
     * Returns a String declaring that the player has placed all his ships
     *
     * @return (String) a String declaring the player has places all his ships
     */
    public String shipArePlaced() {
        return String.format(StringsEn.PLACED_SHIPS, playerName);
    }

    /**
     * Returns a String declaring that the player can play
     *
     * @return (String) a String declaring that the player can play
     */
    public String canPlay() {
        return String.format(StringsEn.CAN_PLAY, playerName);
    }

    /**
     * Returns a String declaring that the player hit a ship
     *
     * @return (String) a String declaring that the player hit a ship
     */
    public String hit() {
        return String.format(StringsEn.HIT, playerName);
    }

    /**
     * Returns a String declaring that the player missed a ship
     *
     * @return (String) a String declaring that the player missed a ship
     */
    public String miss() {
        return String.format(StringsEn.MISS, playerName);
    }

    /**
     * Return a String declaring that the player sank a ship
     *
     * @return (String) a String declaring that the player sank a ship
     */
    public String sank_ship(ShipType shipType) {
        return String.format(StringsEn.SANK_SHIP, playerName, shipName(shipType));
    }

    /**
     * Return a String declaring that the player won the game
     *
     * @return (String) a String declaring that the player won the game
     */
    public String won() {
        return String.format(StringsEn.WINS, playerName);
    }
}