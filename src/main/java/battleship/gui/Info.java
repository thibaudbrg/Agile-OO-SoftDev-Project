package battleship.gui;

import battleship.game.PlayerId;
import battleship.game.ShipType;

public final class Info {
    private final PlayerId playerId;

    public Info(PlayerId playerId) {
        this.playerId = playerId;
    }


    public String whoAmI() {
        return String.format(StringsEn.WHO_AM_I, playerId);
    }

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

    public String willPlayFirst() {
        return String.format(StringsEn.WILL_PLAY_FIRST, playerId);
    }

    public String shipArePlaced() {
        return String.format(StringsEn.PLACED_SHIPS);
    }

    public String canPlay() {
        return String.format(StringsEn.CAN_PLAY);
    }

    public String time_is_up() {
        return String.format(StringsEn.TIME_IS_UP);
    }

    public String hit() {
        return String.format(StringsEn.HIT);
    }

    public String alreadyHit() {
        return String.format(StringsEn.ALREADY_HIT);
    }

    public String miss() {
        return String.format(StringsEn.MISS);
    }

    public String alreadyMissed() {
        return String.format(StringsEn.ALREADY_MISSED);
    }

    public String sankShip(ShipType shipType, int numberOfRemainingShips) {
        return String.format(StringsEn.SANK_SHIP, shipType, numberOfRemainingShips);
    }

    public String won() {
        return String.format(StringsEn.WINS, playerId);
    }

    public String rockHit() {
        return String.format(StringsEn.ROCK_HIT);
    }

    public String hasBomb() {
        return String.format(StringsEn.You_HAVE_A_BOMB);
    }
}