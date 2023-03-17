import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class InfoBoxDialog {

    @Given("I am on the game screen")
    public void i_am_on_the_game_screen() {
        assertTrue(true);
    }

    @Given("a new game has started")
    public void a_new_game_has_started() {
        assertTrue(true);
    }

    @When("it is Player {int}'s turn to place their ships")
    public void it_is_player_s_turn_to_place_their_ships(Integer int1) {
        assertTrue(true);
    }

    @Then("an info box dialog should display the message {string}")
    public void an_info_box_dialog_should_display_the_message(String string) {
        assertTrue(true);
    }

    @Then("the dialog should display the player's name")
    public void the_dialog_should_display_the_player_s_name() {
        assertTrue(true);
    }

    @When("Player {int} will play first")
    public void player_will_play_first(Integer int1) {
        assertTrue(true);
    }

    @When("Player {int} has placed their ships")
    public void player_has_placed_their_ships(Integer int1) {
        assertTrue(true);
    }

    @When("it is Player {int}'s turn to play")
    public void it_is_player_s_turn_to_play(Integer int1) {
        assertTrue(true);
    }

    @When("Player {int} hits a ship")
    public void player_hits_a_ship(Integer int1) {
        assertTrue(true);
    }

    @When("Player {int} misses a ship")
    public void player_misses_a_ship(Integer int1) {
        assertTrue(true);
    }

    @When("Player {int} sinks a ship")
    public void player_sinks_a_ship(Integer int1) {
        assertTrue(true);
    }

    @Then("the dialog should display the name of the ship that was sunk")
    public void the_dialog_should_display_the_name_of_the_ship_that_was_sunk() {
        assertTrue(true);
    }

    @When("Player {int} wins the game")
    public void player_wins_the_game(Integer int1) {
        assertTrue(true);
    }

}
