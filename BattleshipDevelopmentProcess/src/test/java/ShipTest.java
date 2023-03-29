import io.cucumber.java.en.*;
import battleship.game.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShipTest {
    private Ship ship;
    private List<Cell> initialCells;
    private ShipType shipType;
    private boolean shipSunk;

    private Cell newCell;

    @Given("a ship with ship type {string}")
    public void a_ship_with_ship_type(String string) {
        shipType = ShipType.valueOf(string);
        initialCells = new ArrayList<>();
        ship = new Ship(initialCells, shipType);
    }

    @When("I retrieve the ship type")
    public void i_retrieve_the_ship_type() {
        shipType = ship.getShipType();
    }

    @Then("the ship type should be {string}")
    public void the_ship_type_should_be(String string) {
        assertEquals(ShipType.valueOf(string), shipType);
    }

    @Given("a ship with a list of cells")
    public void a_ship_with_a_list_of_cells() {
        initialCells = new ArrayList<>();
        ship = new Ship(initialCells, ShipType.CARRIER);
    }

    @When("I retrieve the list of cells")
    public void i_retrieve_the_list_of_cells() {
        initialCells = ship.getFields();
    }

    @Then("the list of cells should be the same as the initial list")
    public void the_list_of_cells_should_be_the_same_as_the_initial_list() {
        assertEquals(initialCells, ship.getFields());
    }

    @Given("a ship with a list of cells with cell statuses {string}")
    public void a_ship_with_a_list_of_cells_with_cell_statuses(String string) {
        List<CellStatus> cellStatuses = Stream.of(string.split(","))
                .map(CellStatus::valueOf)
                .collect(Collectors.toList());
        initialCells = cellStatuses.stream()
                .map(status -> new Cell(new Coordinates(0, 0), status))
                .collect(Collectors.toList());
        ship = new Ship(initialCells, ShipType.CARRIER);
    }

    @When("I check if the ship has sunk")
    public void i_check_if_the_ship_has_sunk() {
        shipSunk = ship.hasSunk();
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String string) {
        assertEquals(Boolean.parseBoolean(string), shipSunk);
    }

    @When("I add a new cell to the list of cells")
    public void i_add_a_new_cell_to_the_list_of_cells() {
        newCell = new Cell(new Coordinates(0, 0), CellStatus.SHIP);
        ship.add(newCell);
    }

    @Then("the list of cells should include the new cell")
    public void the_list_of_cells_should_include_the_new_cell() {
        assertTrue(ship.getFields().contains(newCell));
    }
}