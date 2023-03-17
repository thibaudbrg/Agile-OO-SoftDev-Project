import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class Player {
    @Given("two players, PLAYER_{int} and PLAYER_{int}")
    public void two_players_player_and_player(Integer int1, Integer int2) {
        assertTrue(true);
    }

    @When("the game starts")
    public void the_game_starts() {
        assertTrue(true);
    }

    @Then("a player is randomly selected to play first")
    public void a_player_is_randomly_selected_to_play_first() {
        assertTrue(true);
    }

    @Then("the other player is the next player")
    public void the_other_player_is_the_next_player() {
        assertTrue(true);
    }

    @When("the current player is PLAYER_{int}")
    public void the_current_player_is_player(Integer int1) {
        assertTrue(true);
    }

    @When("the next player is called")
    public void the_next_player_is_called() {
        assertTrue(true);
    }

    @Then("PLAYER_{int} becomes the current player")
    public void player_becomes_the_current_player(Integer int1) {
        assertTrue(true);
    }

    @Given("a player with a set of ships on their board")
    public void a_player_with_a_set_of_ships_on_their_board() {
        assertTrue(true);
    }

    @When("I request the number of remaining ships for that player")
    public void i_request_the_number_of_remaining_ships_for_that_player() {
        assertTrue(true);
    }

    @Then("the system should return the number of remaining ships")
    public void the_system_should_return_the_number_of_remaining_ships() {
        assertTrue(true);
    }

    @Then("Player {int} should not be able to make any moves")
    public void player_should_not_be_able_to_make_any_moves(Integer int1) {
        assertTrue(true);
    }

    @Then("the game should display a message that it is Player {int}'s turn to play")
    public void the_game_should_display_a_message_that_it_is_player_s_turn_to_play(Integer int1) {
        assertTrue(true);
    }
}
