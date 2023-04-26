package battleship.controller;

import battleship.gui.GameMode;

public interface GameCreationListener {
    void onGameCreate(GameMode gameMode, int numRows, int numCols, boolean isTimed);
}
