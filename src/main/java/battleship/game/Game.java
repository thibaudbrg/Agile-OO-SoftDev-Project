package battleship.game;

import battleship.gui.GameMode;
import battleship.gui.GraphicalGame;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance = null;
    private final List<Player> players;

    private Player currentPlayer;


    private Game(GameMode gameMode, int numCol, int numRow) {
        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();
        List<Board> boards = generateBoards(numCol, numRow);
        this.players = generatePlayers(gameMode, shipsPlayer1, shipsPlayer2, boards);
        currentPlayer = players.get(0);
    }

    public static Game getInstance(GameMode gameMode, int numCol, int numRow) {
        if (instance == null) {
            instance = new Game(gameMode, numCol, numRow);
        }
        return instance;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == players.get(0)) ? players.get(1) : players.get(0);
    }


    public void gameEnded() {
        currentPlayer = null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private List<Board> generateBoards(int numCol, int numRow) {
        if (numCol < 0 || numCol > 20 || numRow < 0 || numRow > 20) {
            throw new RuntimeException("the dimensions are incorrect");
        }
        List<Board> boards = new ArrayList<>();

        Board board1 = new Board(numRow, numCol, true);
        Board board2 = new Board(numRow, numCol, true);
        boards.add(board1);
        boards.add(board2);
        return boards;
    }

    private List<Player> generatePlayers(GameMode gameMode, List<Ship> ships1, List<Ship> ships2, List<Board> boards) {
        Player player1;
        Player player2;
        Board board1 = boards.get(0);
        Board board2 = boards.get(1);

        switch (gameMode) {
            case MULTIPLAYER -> {
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new RealPlayer(ships2, board2, PlayerId.PLAYER_2);
            }
            case EASY -> {
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new AIPlayer(ships2, board2, PlayerId.PLAYER_2, false);
            }
            case HARD -> {
                player1 = new RealPlayer(ships1, board1, PlayerId.PLAYER_1);
                player2 = new AIPlayer(ships2, board2, PlayerId.PLAYER_2, true);
            }
            default -> throw new RuntimeException("The gameMode is Incorrect");
        }

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        return players;
    }
}
