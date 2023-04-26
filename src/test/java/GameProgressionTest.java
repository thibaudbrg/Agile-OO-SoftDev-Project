import battleship.game.GameProgression;
import io.cucumber.java.en.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameProgressionTest {
    private List<GameProgression> gameProgressions;
    private GameProgression progression;
    private int numberOfShips;

    @Given("the GameProgression enum")
    public void the_gameProgression_enum() {
        // No action required, as the enum is already defined.
    }

    @When("I check the possible GameProgressions")
    public void i_check_the_possible_gameProgressions() {
        gameProgressions = Arrays.asList(GameProgression.values());
    }

    @Then("I should see SHIP_PLACEMENT_PLAYER_1, SHIP_PLACEMENT_PLAYER_2 and PLAYING_GAME")
    public void i_should_see_n_s_e_w() {
        assertTrue(gameProgressions.contains(GameProgression.SHIP_PLACEMENT_PLAYER_1));
        assertTrue(gameProgressions.contains(GameProgression.SHIP_PLACEMENT_PLAYER_2));
        assertTrue(gameProgressions.contains(GameProgression.PLAYING_GAME));
    }

    @Given("{int} placed ships")
    public void placed_ships(Integer int1) {
        numberOfShips=int1;
    }

    @When("I check the gameProgression")
    public void i_check_the_game_progression() {
        progression=GameProgression.whichProgression(numberOfShips);

    }

    @Then("It should be {string}")
    public void it_should_be(String string) {
        assertEquals(string,progression.name());
    }
}
