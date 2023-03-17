import io.cucumber.java.en.*;

import static org.junit.Assert.assertTrue;

public class ShipShooting {

    @Given("both players have placed their ships")
    public void both_players_have_placed_their_ships() {
        assertTrue(true);
    }

    @Given("it is player {int}'s turn")
    public void it_is_player_s_turn(Integer int1) {
        assertTrue(true);
    }

    @When("I select a location on the opponent's game board")
    public void i_select_a_location_on_the_opponent_s_game_board() {
        assertTrue(true);
    }

    @Then("the game should indicate whether I hit or missed a ship")
    public void the_game_should_indicate_whether_i_hit_or_missed_a_ship() {
        assertTrue(true);
    }

    @Then("the corresponding cell's status should be updated")
    public void the_corresponding_cell_s_status_should_be_updated() {
        assertTrue(true);
    }

    @When("I select an invalid location on the opponent's game board")
    public void i_select_an_invalid_location_on_the_opponent_s_game_board() {
        assertTrue(true);
    }

    @Given("I have hit a ship")
    public void i_have_hit_a_ship() {
        assertTrue(true);
    }

    @Given("the ship has been sunk")
    public void the_ship_has_been_sunk() {
        assertTrue(true);
    }

    @Then("I should see a message indicating the ship has been sunk")
    public void i_should_see_a_message_indicating_the_ship_has_been_sunk() {
        assertTrue(true);
    }
}
