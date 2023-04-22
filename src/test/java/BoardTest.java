import io.cucumber.java.en.*;
import battleship.game.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    private Player player;
    private Ship ship;
    private Cell accessedCell;
    private boolean shipAdded;


    @Given("a board with size {int} x {int}")
    public void a_board_with_size_x(Integer sizeRow, Integer sizeCol) {
        board = new Board(sizeCol, sizeRow,false);
    }

    /*@When("a ship of size {int} is added at cell \\({int};{int}) with orientation {word}")
    public void a_ship_of_size_is_added_at_cell_with_orientation(Integer shipSize, Integer col, Integer row, String orientation) {
        ship = new Ship(new ArrayList<>(shipSize), ShipType.getShipTypeFromLabel(shipSize));
        Orientation o = null;
        for (Orientation orient : Orientation.values()) {
            if (orient.toString().equals(orientation)) {
                o = orient;
                break;
            }
        }
        shipAdded = player.addShip(ShipType.getShipTypeFromLabel(shipSize),new Coordinates(col, row), o);


    }*/
/*
    @Then("the cells \\({int};{int}), \\({int};{int}) and \\({int};{int}) should have status SHIP")
    public void the_cells_and_should_have_status_ship(Integer col1, Integer row1, Integer col2, Integer row2, Integer col3, Integer row3) {
        assertEquals(CellStatus.SHIP, board.getCell(new Coordinates(col1, row1)).getCellStatus());
        assertEquals(CellStatus.SHIP, board.getCell(new Coordinates(col2, row2)).getCellStatus());
        assertEquals(CellStatus.SHIP, board.getCell(new Coordinates(col3, row3)).getCellStatus());
    }
*/

    /*
    @Given("a ship already placed at \\({int};{int}) to \\({int};{int})")
    public void a_ship_already_placed_at_to(Integer startCol, Integer startRow, Integer endCol, Integer endRow) {
        int shipSize = Math.abs(startCol-endCol)+Math.abs(startRow-endRow)+1;
        ship = new Ship(new ArrayList<>(shipSize), ShipType.getShipTypeFromLabel(shipSize));
        Orientation shipOrient;
        if (startCol.equals(endCol)) {
            shipOrient = startRow < endRow ? Orientation.S : Orientation.N;
        } else {
            shipOrient = startCol < endCol ? Orientation.E : Orientation.W;
        }
        boolean added = player.addShip(ShipType.getShipTypeFromLabel(shipSize),new Coordinates(startCol, startRow), shipOrient);
        assertTrue(added);

    }*/

/*
    @Then("the ship should not be added")
    public void the_ship_should_not_be_added(){
        assertFalse(shipAdded);
    }*/

    @When("the cell at position \\({int};{int}) is accessed")
    public void the_cell_at_position_is_accessed(int col, int row) {
        accessedCell = board.getCell(new Coordinates(col, row));
    }

    @Then("the returned cell should have coordinates \\({int};{int})")
    public void the_returned_cell_should_have_coordinates(int col, int row) {
        assertEquals(col, accessedCell.getCoords().getCol());
        assertEquals(row, accessedCell.getCoords().getRow());
    }

    @When("the size of the board is requested")
    public void the_size_of_the_board_is_requested() {
        int sizeRowCheck = board.getNumCol();
        int sizeColCheck = board.getNumRow();
    }

    @Then("the returned size should be {int} x {int}")
    public void the_returned_size_should_be_x(Integer expectedSizeRow, Integer expectedSizeCol) {
        assertEquals(expectedSizeRow, Integer.valueOf(board.getNumCol()));
        assertEquals(expectedSizeCol, Integer.valueOf(board.getNumRow()));
    }

    @When("the board is filled with cells")
    public void the_board_is_filled_with_cells() {
        assertNotNull(board);
    }

    @Then("the number of cells on the board should be {int}")
    public void the_number_of_cells_on_the_board_should_be(int expectedCellCount) {
        int cellCount = 0;
        for (int row = 0; row < board.getNumRow(); row++) {
            for (int col = 0; col < board.getNumCol(); col++) {
                if (board.getCell(new Coordinates(col, row)) != null) {
                    cellCount++;
                }
            }
        }

        assertEquals(expectedCellCount, cellCount);
    }


    @Then("all cells on the board should have status OCEAN or ROCK")
    public void all_cells_on_the_board_should_have_status_ocean_or_rock() {
        for (int row = 0; row < board.getNumCol(); row++) {
            for (int col = 0; col < board.getNumRow(); col++) {
                assertTrue(board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.OCEAN || board.getCell(new Coordinates(col, row)).getCellStatus() == CellStatus.ROCK);
            }
        }
    }
    @Then("the cell at position \\({int};{int}) should have status OCEAN")
    public void the_cell_at_position_should_have_status_ocean(Integer col, Integer row) {
        assertEquals(CellStatus.OCEAN, board.getCell(new Coordinates(col, row)).getCellStatus());
    }
}
