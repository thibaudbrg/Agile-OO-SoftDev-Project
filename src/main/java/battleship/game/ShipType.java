package battleship.game;

public enum ShipType {
    CARRIER(5,  "Carrier"),
    BATTLESHIP(4,  "Battleship"),
    CRUISER(3,  "Cruiser"),
    SUBMARINE(3,  "Submarine"),
    DESTROYER(2,  "Destroyer");

    private final int size;

    private final String extendedName;

    ShipType(int size, String extendedName) {
        this.size = size;
        this.extendedName = extendedName;
    }

    public Integer getSize() {
        return size;
    }

    public ShipType next() {
        int nextOrdinal = this.ordinal() + 1;
        if (nextOrdinal >= values().length) {
            nextOrdinal = 0;
        }
        return values()[nextOrdinal];
    }

    @Override
    public String toString() {
        return extendedName;
    }
}

