import io.cucumber.java.en.*;
import battleship.game.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class CellStatusTest {
    private List<CellStatus> statuses;

    @Given("the cellStatus enum")
    public void theCellStatusEnum() {
        // No action required, as the enum is already defined.
    }

    @When("I check the possible statuses for a cell")
    public void i_check_the_possible_statuses_for_a_cell() {
        statuses = Arrays.asList(CellStatus.values());
    }

    @Then("I should see O, S, H, and M")
    public void i_should_see_o_s_h_and_m() {
        assertTrue(statuses.contains(CellStatus.OCEAN));
        assertTrue(statuses.contains(CellStatus.SHIP));
        assertTrue(statuses.contains(CellStatus.HIT));
        assertTrue(statuses.contains(CellStatus.MISSED));
    }


}
