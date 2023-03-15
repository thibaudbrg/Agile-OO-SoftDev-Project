import io.cucumber.java.en.*;

public class BoardCellShip {

    @Given("a Board is created")
    public void a_board_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a Ship is created")
    public void a_ship_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("a Cell is created")
    public void a_cell_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Cell should have a status of OCEAN")
    public void the_cell_should_have_a_status_of_ocean() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Cell status can be changed to SHIP, HIT, or MISS")
    public void the_cell_status_can_be_changed_to_ship_hit_or_miss() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the Board is created")
    public void the_board_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Board should be an array of Cells")
    public void the_board_should_be_an_array_of_cells() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("each Cell should have a specific column number, a row number and a status")
    public void each_cell_should_have_a_specific_column_number_a_row_number_and_a_status() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the Ship is created")
    public void the_ship_is_created() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the Ship should be an array of Cells")
    public void the_ship_should_be_an_array_of_cells() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("each Ship should be disposable on the Board")
    public void each_ship_should_be_disposable_on_the_board() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the following ship types exist:")
    public void the_following_ship_types_exist(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @Then("each ship type should have a corresponding length and image.")
    public void each_ship_type_should_have_a_corresponding_length_and_image() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the Ship is placed on the Board")
    public void the_ship_is_placed_on_the_board() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the state of the Cells where the Ship is placed should change from OCEAN to SHIP")
    public void the_state_of_the_cells_where_the_ship_is_placed_should_change_from_ocean_to_ship() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
