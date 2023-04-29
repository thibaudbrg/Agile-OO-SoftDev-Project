package battleship.game;

public enum GameProgression {
    SHIP_PLACEMENT_PLAYER_1, SHIP_PLACEMENT_PLAYER_2, PLAYING_GAME;

    public static GameProgression whichProgression(int nbrShipPlaced) {
        if (nbrShipPlaced < ShipType.values().length) {
            return SHIP_PLACEMENT_PLAYER_1;
        } else if (ShipType.values().length <= nbrShipPlaced && nbrShipPlaced < 2 * ShipType.values().length) {
            return SHIP_PLACEMENT_PLAYER_2;
        } else {
            return PLAYING_GAME;
        }
    }

    public static boolean allShipsPlacedForPlayer(int nbrShipPlaced, int playerNumber) {
        return nbrShipPlaced == playerNumber * ShipType.values().length;
    }
}