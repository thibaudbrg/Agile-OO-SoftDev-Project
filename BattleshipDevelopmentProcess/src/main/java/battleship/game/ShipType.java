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

    public static ShipType getShipTypeFromLabel(Integer label) {
        for (ShipType shipType : ShipType.values()) {
            if (shipType.getLabel().equals(label)) {
                return shipType;
            }
        }
        return null; // return null if no matching ShipType is found
    }
}
