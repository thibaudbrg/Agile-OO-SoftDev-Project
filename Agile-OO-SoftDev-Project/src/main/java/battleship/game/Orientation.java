package battleship.game;

import java.util.List;

public enum Orientation {
    N("North"), S("South"), E("East"), W("West");
    private final String extendedName;

    Orientation(String extendedName) {
        this.extendedName = extendedName;
    }

    @Override
    public String toString() {
        return extendedName;
    }
}
