import battleship.game.Orientation;
import io.cucumber.java.en.*;
import java.util.*;
import static org.junit.Assert.*;

public class OrientationTest {
    private List<Orientation> orientations;
    @Given("the Orientation enum")
    public void the_orientation_enum() {
        // No action required, as the enum is already defined.
    }

    @When("I check the possible orientations for a ship")
    public void i_check_the_possible_orientations_for_a_ship() {
        orientations = Arrays.asList(Orientation.values());
    }

    @Then("I should see N,S,E,W")
    public void i_should_see_n_s_e_w() {
        assertTrue(orientations.contains(Orientation.S));
        assertTrue(orientations.contains(Orientation.N));
        assertTrue(orientations.contains(Orientation.E));
        assertTrue(orientations.contains(Orientation.W));
    }

}
