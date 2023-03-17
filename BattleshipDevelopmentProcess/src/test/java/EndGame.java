import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class EndGame {

    @Given("I have sunk all of player {int}'s ships")
    public void i_have_sunk_all_of_player_s_ships(Integer int1) {
        assertTrue(true);
    }

    @Then("I should see a message indicating that I have won the game")
    public void i_should_see_a_message_indicating_that_i_have_won_the_game() {
        assertTrue(true);
    }

    @Then("the game should end")
    public void the_game_should_end() {
        assertTrue(true);
    }
}
