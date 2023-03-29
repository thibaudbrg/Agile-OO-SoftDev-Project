package battleship.game;

import java.util.List;

public enum Orientation {
    N("North"), S("South"), E("East"), W("West");
    private String extendedName;

    public static List<Orientation> ALL = List.of(Orientation.values());

    private Orientation(String extendedName) {
        this.extendedName = extendedName;
    }

    @Override
    public String toString() {
        return extendedName;
    }
}
