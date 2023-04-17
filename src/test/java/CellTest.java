import io.cucumber.java.en.*;
import battleship.game.*;
import static org.junit.Assert.*;

public class CellTest {
    private Cell cell;
    private int column;
    private int row;
    private CellStatus status;

    @Given("a column value of {int}, row value of {int} and status of OCEAN")
    public void a_column_value_of_row_value_of_and_status_of_ocean(Integer int1, Integer int2) {
        column = int1;
        row = int2;
        status = CellStatus.OCEAN;
    }

    @When("a cell is created with these parameters")
    public void a_cell_is_created_with_these_parameters() {
        cell = new Cell(new Coordinates(column, row), status);
    }

    @Then("the cell's column value should be {int}")
    public void the_cell_s_column_value_should_be(Integer int1) {
        assertEquals(int1.intValue(), cell.getCoords().getCol());
    }

    @Then("the cell's row value should be {int}")
    public void the_cell_s_row_value_should_be(Integer int1) {
        assertEquals(int1.intValue(), cell.getCoords().getRow());
    }

    @Then("the cell's status should be OCEAN")
    public void the_cell_s_status_should_be_ocean() {
        assertEquals(CellStatus.OCEAN, cell.getCellStatus());
    }

    @Given("a cell with status of OCEAN")
    public void a_cell_with_status_of_ocean() {
        cell = new Cell(new Coordinates(1, 1), CellStatus.OCEAN);
    }

    @When("the cell's status is changed to SHIP")
    public void the_cell_s_status_is_changed_to_ship() {
        cell.setCellStatus(CellStatus.SHIP);
    }

    @Then("the cell's status should be SHIP")
    public void the_cell_s_status_should_be_ship() {
        assertEquals(CellStatus.SHIP, cell.getCellStatus());
    }

    @Given("a cell with column value of {int} and row value of {int}")
    public void a_cell_with_column_value_of_and_row_value_of(Integer int1, Integer int2) {
        cell = new Cell(new Coordinates(int1, int2), CellStatus.OCEAN);
    }

    @When("the cell's column and row value is retrieved")
    public void the_cell_s_column_and_row_value_is_retrieved() {
        column = cell.getCoords().getCol();
        row = cell.getCoords().getRow();
    }

    @Then("the column value should be {int}")
    public void the_column_value_should_be(Integer int1) {
        assertEquals(int1.intValue(), column);
    }

    @Then("the row value should be {int}")
    public void the_row_value_should_be(Integer int1) {
        assertEquals(int1.intValue(), row);
    }

    @Given("a cell with status of SHIP")
    public void a_cell_with_status_of_ship() {
        cell = new Cell(new Coordinates(1, 1), CellStatus.SHIP);
    }

    @When("the cell's status is retrieved")
    public void the_cell_s_status_is_retrieved() {
        status = cell.getCellStatus();
    }

    @Then("the status should be SHIP")
    public void the_status_should_be_ship() {
        assertEquals(CellStatus.SHIP, status);
    }
}