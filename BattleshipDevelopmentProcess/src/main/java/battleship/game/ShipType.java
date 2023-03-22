package battleship.game;

public enum ShipType {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    DESTROYER(2);

    public final Integer label;

    ShipType(Integer label) {
        this.label = label;
    }
}
