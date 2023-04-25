import battleship.game.Game;
import battleship.gui.GameMode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class GameTest {

    int numCol;
    int numRow;

    GameMode gameMode;

    @Given("the game board is generated with numCol of {int} and numRow of {int}")
    public void the_game_board_is_generated_with_num_col_of_and_num_row_of(Integer int1, Integer int2) {
        numCol = int1;
        numRow = int2;

    }


    @Then("a RuntimeException is thrown when we play the game")
    public void a_runtime_exception_is_thrown_when_we_play_the_game() {
        assertThrows(RuntimeException.class,()->Game.play(gameMode,numCol,numRow,false));
    }

    @Given("a correct GameMode")
    public void a_correct_game_mode() {
        gameMode = GameMode.HARD;
    }

    @Given("correct dimensions")
    public void correct_dimensions() {
        numCol=10;
        numRow=10;

    }

    @Given("a null mode")
    public void a_null_mode() {
        gameMode=null;
    }




}
