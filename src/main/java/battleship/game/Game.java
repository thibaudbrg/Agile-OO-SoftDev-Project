package battleship.game;

import battleship.gui.GameMode;
import battleship.gui.GraphicalGame;

import java.util.ArrayList;
import java.util.List;

public class Game {


    private Game() {
    }

    public static void play(GameMode gameMode, int numCol , int numRow) {


        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();

        List<Board> boards = generateBoards(numCol,numRow);

        List<Player> players = generatePlayers(gameMode, shipsPlayer1, shipsPlayer2, boards);

        GraphicalGame.initial(gameMode, players);
    }

    private static List<Board> generateBoards(int numCol, int numRow) {
        List<Board> boards = new ArrayList<>();

        Board board1 = new Board(numRow, numCol);
        Board board2 = new Board(numRow, numCol);
        boards.add(board1);
        boards.add(board2);
        return boards;
    }

    private static List<Player> generatePlayers(GameMode gameMode, List<Ship> ships1, List<Ship> ships2, List<Board> boards) {
        Player player1 = null;
        Player player2 = null;
        Board board1 = boards.get(0);
        Board board2 = boards.get(1);

        switch (gameMode) {
            case MULTIPLAYER:
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new RealPlayer(ships2, board2, PlayerId.PLAYER_2);
                break;
            case EASY, HARD:
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new AIPlayer(ships2, board2, PlayerId.PLAYER_2);
                break;
        }

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        return players;
    }
}
