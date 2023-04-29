import battleship.game.Game;
import battleship.gui.GameMode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameTest {

    int numCol;
    int numRow;

    GameMode gameMode;
    @Given("correct dimensions")
    public void correct_dimensions() {
        numCol =10;
        numRow = 10;

    }

    @Given("a null mode")
    public void a_null_mode() {
        gameMode = null;

    }





    @Then("a RuntimeException is thrown when we create the game")
    public void a_runtime_exception_is_thrown_when_we_create_the_game() {
        assertThrows(RuntimeException.class, ()->  Game.getInstance(gameMode, numCol,numRow));

    }

    @Given("a correct {string}")
    public void a_correct(String string) {
        switch (string) {
            case "MULTIPLAYER" -> gameMode = GameMode.MULTIPLAYER;
            case "EASY" -> gameMode = GameMode.EASY;
            case "HARD" -> gameMode = GameMode.HARD;
        }
    }

    @Then("no RuntimeException is thrown when we create the game")
    public void no_runtime_exception_is_thrown_when_we_create_the_game() {
        assertDoesNotThrow(()-> Game.getInstance(gameMode, numCol, numRow));

    }

    @Given("the game board is generated with numCol of {int} and numRow of {int}")
    public void the_game_board_is_generated_with_num_col_of_and_num_row_of(Integer int1, Integer int2) {
        numCol = int1;
        numRow = int2;

    }

    @Given("a correct GameMode")
    public void aCorrectGameMode() {
        gameMode=GameMode.MULTIPLAYER;

    }
}
