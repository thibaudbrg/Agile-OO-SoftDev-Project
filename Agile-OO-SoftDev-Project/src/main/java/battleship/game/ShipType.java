package battleship.game;

import javafx.scene.image.Image;

import java.util.Objects;
import java.util.Random;

public enum ShipType {
    CARRIER(5, "assets/carrier.png"),
    BATTLESHIP(4, "assets/battleship.png"),
    CRUISER(3, "assets/cruiser.png"),
    SUBMARINE(3, "assets/submarine.png"),
    DESTROYER(2, "assets/destroyer.png");

    private static final Random random = new Random();
    private final int size;
    private final Image image;

    private ShipType(int size, String assetName) {
        this.size = size;
        this.image = new Image(Objects.requireNonNull(ShipType.class.getClassLoader().getResourceAsStream(assetName)));
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
}
