package battleship.game;

public enum ShipType {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);

    private final Integer label;

    ShipType(Integer label) {
        this.label = label;
    }

    public Integer getLabel() {
        return label;
    }
}
