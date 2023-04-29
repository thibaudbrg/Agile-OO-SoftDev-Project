import io.cucumber.java.en.*;
import battleship.game.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PlayerTest {

    private RealPlayer realPlayer;
    private List<Ship> remainingShips;
    private int numberOfRemainingShips;
    private int beforeActionPlayer1RemainingShip;
    private Board board;
    private RealPlayer realPlayer1;
    private RealPlayer realPlayer2;

    private boolean playerPlacesWorked;

    @Given("a player with {string} remaining ships")
    public void a_player_with_remaining_ships(String string) {
        List<Ship> remainingShips = new ArrayList<>(Integer.parseInt(string));
        for (int i = 0; i < Integer.parseInt(string); i++) {
            remainingShips.add(new Ship(new ArrayList<>(), ShipType.BATTLESHIP));
        }
        board = new Board(10, 10,false);
        this.realPlayer = new RealPlayer(remainingShips, board,PlayerId.PLAYER_2);
    }

    @When("I retrieve the number of remaining ships")
    public void i_retrieve_the_number_of_remaining_ships() {
        numberOfRemainingShips = realPlayer.getNumberOfRemainingShips();
    }

    @Then("the number of remaining ships should be {string}")
    public void the_number_of_remaining_ships_should_be(String string) {
        int expectedRemainingShips = Integer.parseInt(string);
        assertEquals(expectedRemainingShips, numberOfRemainingShips);
    }

    @Given("a player with a board")
    public void a_player_with_a_board() {
        List<Ship> remainingShips = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            remainingShips.add(new Ship(new ArrayList<>(), ShipType.BATTLESHIP));
        }
        board = new Board(10, 10,false);
        this.realPlayer = new RealPlayer(remainingShips, board,PlayerId.PLAYER_1);
    }

    @When("I retrieve the player's board")
    public void i_retrieve_the_player_s_board() {
        this.board = realPlayer.getBoard();
    }

    @Then("the board should not be null")
    public void the_board_should_not_be_null() {
        assertNotNull(board);
    }

    @Given("two players")
    public void twoPlayers() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(new Coordinates(2, 2), CellStatus.SHIP));
        cells.add(new Cell(new Coordinates(2, 3), CellStatus.SHIP));
        Board board1 = new Board(10, 10,false);
        List<Ship> remainingShips1 = new ArrayList<>();

        remainingShips1.add(new Ship(cells, ShipType.DESTROYER));

        realPlayer1 = new RealPlayer(remainingShips1, board1,PlayerId.PLAYER_1);

        Board board2 = new Board(10, 10,false);
        List<Ship> remainingShips2 = new ArrayList<>();

        remainingShips2.add(new Ship(cells, ShipType.DESTROYER));

        realPlayer2 = new RealPlayer(remainingShips2, board2,PlayerId.PLAYER_1);

    }

    @And("the first Player has a cell at {string}, {string} with a cell status of {string}")
    public void theFirstPlayerHasACellAtWithACellStatusOf(String col, String row, String status) {
        realPlayer1.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).setCellStatus(CellStatus.valueOf(status));

    }

    @When("the second Player shoots at {string}, {string}")
    public void theSecondPlayerShootsAt(String arg0, String arg1) {
        realPlayer2.handleShot(new Coordinates(Integer.parseInt(arg0), Integer.parseInt(arg1)), realPlayer1);
    }

    @Then("the cell status of first player at {string}, {string} should be {string}")
    public void theCellStatusOfFirstPlayerAtShouldBe(String col, String row, String arg2) {
        CellStatus status = realPlayer1.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).getCellStatus();
        assertEquals(status, CellStatus.valueOf(arg2));
    }

    @Then("the cell status of player at {string}, {string} should be {string}")
    public void theCellStatusOfPlayerAtShouldBe(String col, String row, String arg2) {
        CellStatus status = realPlayer.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).getCellStatus();
        assertEquals(status, CellStatus.valueOf(arg2));
    }


    @And("the first player has a ship at {int}, {int} with an orientation of {string} and a type {string}")
    public void theFirstPlayerHasAShipAtWithAnOrientationOfAndAType(int i1, int i2, String o, String st) {
        Ship ship = new Ship(new ArrayList<>(), ShipType.valueOf(st));
        realPlayer1.addShip(ShipType.valueOf(st),new Coordinates(i1, i2), Orientation.valueOf(o));
        beforeActionPlayer1RemainingShip = realPlayer1.getNumberOfRemainingShips();
    }


    @Then("the number of first player's remaining ships should stay the same")
    public void the_number_of_first_player_s_remaining_ships_should_stay_the_same() {
        assertEquals(beforeActionPlayer1RemainingShip, realPlayer1.getNumberOfRemainingShips());
    }

    @And("the the second Player shoots at {string}, {string}")
    public void theTheSecondPlayerShootsAt(String arg0, String arg1) {
        realPlayer2.handleShot(new Coordinates(Integer.parseInt(arg0), Integer.parseInt(arg1)), realPlayer1);

    }

    @Then("the number of first player's remaining ships should decrease by {int}")
    public void the_number_of_first_player_s_remaining_ships_should_decrease_by(Integer int1) {
        assertEquals(beforeActionPlayer1RemainingShip - int1, realPlayer1.getNumberOfRemainingShips());
    }

    @Given("the player has a ship at {string}, {string} with an orientation of {string} and a type {string}")
    public void the_player_has_a_ship_at_with_an_orientation_of_and_a_type(String i1, String i2, String o, String st) {
        Ship ship = new Ship(new ArrayList<>(), ShipType.valueOf(st));
        realPlayer.addShip(ShipType.valueOf(st),new Coordinates(Integer.parseInt(i1), Integer.parseInt(i2)), Orientation.valueOf(o));
        realPlayer.getRemainingShips().add(ship);
    }

    @When("the player attempts to place a ship at {string}, {string} with an orientation of {string}")
    public void the_player_attempts_to_place_a_ship_at_with_an_orientation_of(String i1, String i2, String o) {
        playerPlacesWorked = realPlayer.addShip(ShipType.CARRIER,new Coordinates(Integer.parseInt(i1), Integer.parseInt(i2)), Orientation.valueOf(o));

    }

    @Then("the placement should fail")
    public void the_placement_should_fail() {
        assertFalse(playerPlacesWorked);
    }

    @Then("the placement should succeed")
    public void the_placement_should_succeed() {
        assertTrue(playerPlacesWorked);
    }

    @Given("the player's Board has a Rock at {string}, {string}")
    public void the_player_s_board_has_a_rock_at(String col, String row) {
       this.realPlayer.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).setCellStatus(CellStatus.ROCK);
    }

    @Given("the second Player has a Rock at {int} , {int}")
    public void the_second_player_has_a_rock_at(Integer col, Integer row) {
        realPlayer2.getBoard().getCell(new Coordinates(col,row)).setCellStatus(CellStatus.ROCK);
    }

    @When("the first player shoots at {int} , {int}")
    public void the_first_player_shoots_at(Integer col, Integer row) {
        realPlayer1.handleShot(new Coordinates(col,row),realPlayer2);
    }

    @Then("the second Player must have a bomb")
    public void the_second_player_must_have_a_bomb() {
        assertTrue(realPlayer2.getHasBomb());
    }

    @Given("the first player has a bomb")
    public void the_first_player_has_a_bomb() {
        realPlayer1.setHasBomb(true);
    }

    @Then("the neighboring cells of {int}, {int} have a status different from ocean")
    public void the_neighboring_cells_of_have_a_status_different_from(Integer int1, Integer int2) {

        List<Coordinates> neighbors = realPlayer2.neighbouringCoordinates(new Coordinates(int1, int2));
        boolean different = true;
        for (Coordinates coord: neighbors) {
            if (realPlayer2.getBoard().getCell(coord).getCellStatus() == CellStatus.OCEAN){
                different = false;
                break;
            }
        }
        assertTrue(different);

    }

}
