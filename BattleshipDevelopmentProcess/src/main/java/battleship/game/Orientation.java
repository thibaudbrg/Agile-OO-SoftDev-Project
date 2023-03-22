package battleship.game;

public enum Orientation {
    N("North"), S("South"), E("East"), W("West");
    private String extendedName;

    private Orientation(String extendedName) {
        this.extendedName = extendedName;
    }

    @Override
    public String toString() {
        return extendedName ;
    }
}
