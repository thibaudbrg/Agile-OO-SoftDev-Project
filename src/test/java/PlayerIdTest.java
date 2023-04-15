import io.cucumber.java.en.*;
import battleship.game.*;
import static org.junit.Assert.*;

import java.util.List;

public class PlayerIdTest {
    private PlayerId currentPlayer;
    private PlayerId nextPlayer;
    private List<PlayerId> allPlayerIds;
    private int playerIdCount;

    @Given("the PlayerId enum")
    public void the_player_id_enum() {
        // No action required, as the enum is already defined.
    }

    @When("I retrieve the count of player IDs")
    public void i_retrieve_the_count_of_player_i_ds() {
        playerIdCount = PlayerId.COUNT;
    }

    @Then("the count should be {string}")
    public void the_count_should_be(String string) {
        assertEquals(Integer.parseInt(string), playerIdCount);
    }

    @Given("a PlayerId {string}")
    public void a_player_id(String string) {
        currentPlayer = PlayerId.valueOf(string);
    }

    @When("I retrieve the next player")
    public void i_retrieve_the_next_player() {
        nextPlayer = currentPlayer.next();
    }

    @Then("the next player should be {string}")
    public void the_next_player_should_be(String string) {
        assertEquals(PlayerId.valueOf(string), nextPlayer);
    }

    @When("I retrieve all player IDs")
    public void i_retrieve_all_player_i_ds() {
        allPlayerIds = PlayerId.ALL;
    }

    @Then("the list of player IDs should contain {string} and {string}")
    public void the_list_of_player_i_ds_should_contain_and(String string1, String string2) {
        assertTrue(allPlayerIds.contains(PlayerId.valueOf(string1)));
        assertTrue(allPlayerIds.contains(PlayerId.valueOf(string2)));
    }
}