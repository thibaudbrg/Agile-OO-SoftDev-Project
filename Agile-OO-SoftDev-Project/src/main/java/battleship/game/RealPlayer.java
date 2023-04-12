package battleship.game;

import java.util.List;
import java.util.Scanner;

public class RealPlayer extends Player{
    public RealPlayer(List<Ship> remainingShips, Board board , PlayerId playerId) {
        super(remainingShips, board,playerId);
    }
}

