package battleship.gui;

import battleship.game.PlayerId;
import battleship.game.ShipType;

/**
 * Generate most of the in-game messages
 */
public final class Info {
    private final PlayerId playerId;

    /**
     * Public constructor
     *
     * @param playerId of the player
     */
    public Info(PlayerId playerId) {
        this.playerId = playerId;
    }


    public String whoAmI() {
        return String.format(StringsEn.WHO_AM_I, playerId);
    }

    /**
     * Returns a String declaring that the player needs to choose the ships placements
     *
     * @return (String) a String declaring that the player needs to choose the ships placements
     */
    public String chooseShipsPlacements() {
        return String.format(StringsEn.CHOOSE_SHIP_PLACEMENTS);
    }

    public String chooseFirstShipPlacement(ShipType shipType) {
        return String.format(StringsEn.CHOOSE_FIRST_SHIP_PLACEMENT, shipType, shipType.getSize());
    }

    public String chooseSpecificShipPlacement(ShipType shipType) {
        return String.format(StringsEn.CHOOSE_SPECIFIC_SHIP_PLACEMENT, shipType, shipType.getSize());
    }

    public String placementCollision() {
        return String.format(StringsEn.PLACEMENT_COLLISION);
    }

    public String goodPlacement() {
        return String.format(StringsEn.GOOD_PLACEMENT);
    }

    /**
     * Returns a String declaring which player plays first
     *
     * @return (String) a String declaring which player plays first
     */
    public String willPlayFirst() {
        return String.format(StringsEn.WILL_PLAY_FIRST, playerId);
    }


    /**
     * Returns a String declaring that the player has placed all his ships
     *
     * @return (String) a String declaring the player has places all his ships
     */
    public String shipArePlaced() {
        return String.format(StringsEn.PLACED_SHIPS);
    }

    /**
     * Returns a String declaring that the player can play
     *
     * @return (String) a String declaring that the player can play
     */
    public String canPlay() {
        return String.format(StringsEn.CAN_PLAY);
    }

    /**
     * Returns a String declaring that the player hit a ship
     *
     * @return (String) a String declaring that the player hit a ship
     */
    public String hit() {
        return String.format(StringsEn.HIT);
    }

    public String alreadyHit() {
        return String.format(StringsEn.ALREADY_HIT);
    }

    /**
     * Returns a String declaring that the player missed a ship
     *
     * @return (String) a String declaring that the player missed a ship
     */
    public String miss() {
        return String.format(StringsEn.MISS);
    }

    public String alreadyMissed() {
        return String.format(StringsEn.ALREADY_MISSED);
    }

    /**
     * Return a String declaring that the player sank a ship
     *
     * @return (String) a String declaring that the player sank a ship
     */
    public String sankShip(ShipType shipType, int numberOfRemainingShips) {
        return String.format(StringsEn.SANK_SHIP, shipType, numberOfRemainingShips);
    }

    /**
     * Return a String declaring that the player won the game
     *
     * @return (String) a String declaring that the player won the game
     */
    public String won() {
        return String.format(StringsEn.WINS, playerId);
    }

    public String rockHit() {
        return String.format(StringsEn.ROCK_HIT);
    }
}