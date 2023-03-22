package battleship.game;

import java.util.List;

public enum PlayerId {
    PLAYER_1,
    PLAYER_2;
    public static final List<PlayerId> ALL = List.of(PlayerId.values());
    public static final int COUNT = ALL.size();

    /**
     * returns the next player
     * @return the next player
     */
    public PlayerId next() {
        return (this == PLAYER_1) ? PLAYER_2 : PLAYER_1;
    }
}
