import io.cucumber.java.en.*;
import battleship.game.*;
import static org.junit.Assert.*;

public class ShipTypeTest {
    private ShipType shipType;
    private Integer shipLabel;

    @Given("a ShipType {string}")
    public void a_ship_type(String string) {
        shipType = ShipType.valueOf(string);
    }

    @When("I retrieve the label of the ship type")
    public void i_retrieve_the_label_of_the_ship_type() {
        shipLabel = shipType.getLabel();
    }

    @Then("the label should be {string}")
    public void the_label_should_be(String string) {
        assertEquals(Integer.valueOf(string), shipLabel);
    }
}