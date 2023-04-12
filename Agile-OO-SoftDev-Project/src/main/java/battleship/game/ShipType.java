package battleship.game;

import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Random;

public enum ShipType {
    CARRIER(5, "assets/carrier.png", "Carrier"),
    BATTLESHIP(4, "assets/battleship.png", "Battleship"),
    CRUISER(3, "assets/cruiser.png", "Cruiser"),
    SUBMARINE(3, "assets/submarine.png", "Submarine"),
    DESTROYER(2, "assets/destroyer.png", "Destroyer");

    private static final Random random = new Random();
    private final int size;
    private final Image image;

    private final String extendedName;

    private ShipType(int size, String assetName, String extendedName) {
        this.size = size;
        this.image = new Image(Objects.requireNonNull(ShipType.class.getClassLoader().getResourceAsStream(assetName)));
        this.extendedName = extendedName;
    }
    public Integer getSize() {
        return size;
    }

    public Image getImage() {
        return image;
    }


    public static ShipType getShipTypeFromLabel(Integer label) {
        for (ShipType shipType : ShipType.values()) {
            if (shipType.getSize().equals(label)) {
                return shipType;
            }
        }
        return null; // return null if no matching ShipType is found
    }

    public static ShipType random() {
        return values()[random.nextInt(values().length)];
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
