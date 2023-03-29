package battleship.gui;

public enum GameMode {
    MULTIPLAYER("Multiplayer"), EASY("Easy mode"), HARD("Hard mode");

    private String extendedName;

    private GameMode(String extendedName) {
        this.extendedName = extendedName;
    }

    public String getExtendedName() {
        return extendedName;
    }
}