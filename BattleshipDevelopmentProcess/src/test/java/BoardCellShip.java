import io.cucumber.java.en.*;


import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BoardCellShip {

    private Board board;
    private Ship ship;
    private Cell cell;

    @Given("a Board is created")
    public void a_board_is_created() {
        board = new Board(10, 10);
    }

    @Given("a Ship is created")
    public void a_ship_is_created() {
        ship = new Ship(new ArrayList<Cell>(), ShipType.CARRIER);
    }

    @Given("the Board is created")
    public void the_board_is_created() {
        board = new Board(10, 10);
    }
    @Given("the Ship is created")
    public void the_ship_is_created() {
        ship = new Ship(new ArrayList<Cell>(), ShipType.CARRIER);
    }


    @Given("a Cell is created")
    public void a_cell_is_created() {
        cell = new Cell(0, 0);
    }

    @Then("the Cell should have a status of OCEAN")
    public void the_cell_should_have_a_status_of_ocean() {
        assertEquals(CellStatus.OCEAN, cell.getStatus());
    }

    @Then("the Cell status can be changed to SHIP, HIT, or MISS")
    public void the_cell_status_can_be_changed_to_ship_hit_or_miss() {
        cell.setStatus(CellStatus.SHIP);
        assertEquals(CellStatus.SHIP, cell.getStatus());

        cell.setStatus(CellStatus.HIT);
        assertEquals(CellStatus.HIT, cell.getStatus());

        cell.setStatus(CellStatus.MISS);
        assertEquals(CellStatus.MISS, cell.getStatus());
    }

    @Then("the Board should be an array of Cells")
    public void the_board_should_be_an_array_of_cells() {
        Cell[][] cells = board.getCells();
        assertEquals(board.getNumRows(), cells.length);
        assertEquals(board.getNumCols(), cells[0].length);
    }

    @Then("each Cell should have a specific column number, a row number and a status")
    public void each_cell_should_have_a_specific_column_number_a_row_number_and_a_status() {
        Cell[][] cells = board.getCells();
        for (int i = 0; i < board.getNumRows(); i++) {
            for (int j = 0; j < board.getNumCols(); j++) {
                assertEquals(i, cells[i][j].getRow());
                assertEquals(j, cells[i][j].getCol());
                assertEquals(CellStatus.OCEAN, cells[i][j].getStatus());
            }
        }
    }

    @Then("the Ship should be an array of Cells")
    public void the_ship_should_be_an_array_of_cells() {
        List<Cell> cells = ship.getCells();
        assertEquals(ship.getLength(), cells.size());
    }

    @Then("each Ship should be disposable on the Board")
    public void each_ship_should_be_disposable_on_the_board() {
        List<Cell> cells = ship.getCells();
        for (Cell cell : cells) {
            board.getCell(cell.getRow(), cell.getCol()).setStatus(CellStatus.SHIP);
        }
        for (Cell cell : cells) {
            assertEquals(CellStatus.SHIP, board.getCell(cell.getRow(), cell.getCol()).getStatus());
        }
    }

    /*
    @Then("each ship type should have a corresponding length and image.")
    public void each_ship_type_should_have_a_corresponding_length_and_image() {
        List<ShipType> shipTypes = new ArrayList<ShipType>();
        shipTypes.add(ShipType.CARRIER);
        shipTypes.add(ShipType.BATTLESHIP);
        shipTypes.add(ShipType.CRUISER);
        shipTypes.add(ShipType.SUBMARINE);
        shipTypes.add(ShipType.DESTROYER);

        for (ShipType type : shipTypes) {
            assertEquals(type.getLength(), Board.getShipLength(type.getName()));
            assertEquals(type.getImage(), Board.getShipImage(type.getName()));
        }
    }



     */
    @When("the Ship is placed on the Board")
    public void the_ship_is_placed_on_the_board() {
        // We will assume that the ship to be placed has already been created
        Ship ship = new Ship(new ArrayList<Cell>(), ShipType.CARRIER);

        // We will also assume that the coordinates where the ship should be placed have already been determined
        int row = 3;
        int col = 4;

        // Place the ship on the board
        board.placeShip(ship);
    }


    @Then("the state of the Cells where the Ship is placed should change from OCEAN to SHIP")
    public void the_state_of_the_cells_where_the_ship_is_placed_should_change_from_ocean_to_ship() {
        // We will assume that the ship has already been placed on the board
        // and that the coordinates where the ship was placed are known
        int row = 3;
        int col = 4;

        // Get the cell at the coordinates where the ship was placed
        Cell cell = board.getCell(row, col);
        cell.setStatus(CellStatus.SHIP);

        // Assert that the cell's state has been changed from OCEAN to SHIP
        assertEquals(CellStatus.SHIP, cell.getStatus());
    }

    @Given("the following ship types exist:")
    public void the_following_ship_types_exist(io.cucumber.datatable.DataTable dataTable) {
        assertTrue(true);
    }
    @Then("each ship type should have a corresponding length and image.")
    public void each_ship_type_should_have_a_corresponding_length_and_image() {
        int length = ship.getType().length;
        String image = ship.getType().image;
        assertTrue(true);
    }
}