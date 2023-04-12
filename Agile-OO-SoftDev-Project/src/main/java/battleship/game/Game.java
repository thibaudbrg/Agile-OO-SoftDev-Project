package battleship.game;

import battleship.gui.GameMode;
import battleship.gui.GraphicalGame;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import static battleship.game.Display.*;

public class Game {


    private Game() {
    }

    public static void play(GameMode gameMode, int rows , int cols) {


        final List<Ship> shipsPlayer1 = new ArrayList<>();
        final List<Ship> shipsPlayer2 = new ArrayList<>();

        List<Board> boards = generateBoards(rows,cols);

        List<Player> players = generatePlayers(gameMode, shipsPlayer1, shipsPlayer2, boards);
        //Player player1 = players.get(0);
        //Player player2 = players.get(1);

        GraphicalGame.initial(gameMode, players);
    }

    private static List<Board> generateBoards(int rows, int cols) {
        List<Board> boards = new ArrayList<>();

        Board board1 = new Board(cols, rows);
        Board board2 = new Board(cols, rows);
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
