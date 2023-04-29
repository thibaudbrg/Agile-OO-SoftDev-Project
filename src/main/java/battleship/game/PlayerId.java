package battleship.game;

import java.util.List;

public enum PlayerId {
    PLAYER_1(1, "Player 1"),
    PLAYER_2(2, "Player 2");
    public static final List<PlayerId> ALL = List.of(PlayerId.values());
    public static final int COUNT = ALL.size();

    private final int playerNumber;
    private final String extendedName;

    public PlayerId next() {
        return (this == PLAYER_1) ? PLAYER_2 : PLAYER_1;
    }


    PlayerId(int playerNumber, String extendedName) {
        this.playerNumber = playerNumber;
        this.extendedName = extendedName;
    }

    @Override
    public String toString() {
        return extendedName;
    }
}
