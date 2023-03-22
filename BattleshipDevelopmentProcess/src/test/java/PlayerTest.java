import io.cucumber.java.en.*;
import battleship.game.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PlayerTest{
    /*
    private Player player;
    private List<Ship> remainingShips;
    private Board board;
    private Player attacker;
    private Player defender;

    @Given("a player with {string} remaining ships")
    public void a_player_with_remaining_ships(String string) {
        List<Ship> remainingShips = new ArrayList<>(Integer.parseInt(string));
        board = new Board(10, 10);
        this.player = new Player(remainingShips, board);
    }

    @When("I retrieve the number of remaining ships")
    public void i_retrieve_the_number_of_remaining_ships() {
        remainingShips = player.getRemainingShips();
    }

    @Then("the number of remaining ships should be {string}")
    public void the_number_of_remaining_ships_should_be(String string) {
        int expectedRemainingShips = Integer.parseInt(string);
        assertEquals(expectedRemainingShips, remainingShips);
    }

    @Given("a player with a board")
    public void a_player_with_a_board() {
        this.player = new Player(5);
        this.player.setBoard(new Board());
    }

    @When("I retrieve the player's board")
    public void i_retrieve_the_player_s_board() {
        this.board = player.getBoard();
    }

    @Then("the board should not be null")
    public void the_board_should_not_be_null() {
        assertNotNull(board);
    }

    @Given("two players, {string} and {string}")
    public void two_players_and(String attackerName, String defenderName) {
        this.attacker = new Player(attackerName, 5);
        this.defender = new Player(defenderName, 5);
    }

    @Given("the Defender has a cell at {string}, {string} with a cell status of {string}")
    public void the_defender_has_a_cell_at_with_a_cell_status_of(String col, String row, String initialCellStatus) {
        int column = Integer.parseInt(col);
        int rowNum = Integer.parseInt(row);
        CellStatus status = CellStatus.valueOf(initialCellStatus);
        defender.getBoard().getCell(column, rowNum).setStatus(status);
    }

    @When("the Attacker shoots at {string}, {string}")
    public void the_attacker_shoots_at(String col, String row) {
        int column = Integer.parseInt(col);
        int rowNum = Integer.parseInt(row);
        this.message = attacker.attack(defender, new Coordinate(column, rowNum));
    }

    @Then("the cell status should be {string}")
    public void the_cell_status_should_be(String finalCellStatus) {
        CellStatus expectedStatus = CellStatus.valueOf(finalCellStatus);
        assertEquals(expectedStatus, defender.getBoard().getCell(column, rowNum).getStatus());
    }

    @Then("the displayed message should be {string}")
    public void the_displayed_message_should_be(String message) {
        assertEquals(message, this.message);
    }

    @Given("the Defender has a ship at {string}, {string} with a cell status of {string}")
    public void the_defender_has_a_ship_at_with_a_cell_status_of(String col, String row, String cellStatus) {
        int column = Integer.parseInt(col);
        int rowNum = Integer.parseInt(row);
        CellStatus status = CellStatus.valueOf(cellStatus);
        defender.getBoard().getCell(column, rowNum).setStatus(status);
        defender.getBoard().getCell(column, rowNum).setShip(new Ship(1, Orientation.HORIZONTAL));
    }

    @Given("the ship has {string} remaining hit point")
    public void the_ship_has_remaining_hit_point(String string) {
        int remainingHitPoints = Integer.parseInt(string);
        defender.getBoard().getCell(column, rowNum).getShip().setRemainingHitPoints(remainingHitPoints);
    }

    @Then("the displayed message should contain {string}")
    public void the_displayed_message_should_contain(String string) {
        assertTrue(this.message.contains(string));
    }

    @Then("the number of Defender's remaining ships should decrease by {string}")
    public void the_number_of_defender_s_remaining_ships_should_decrease_by(String string) {
        int decrease = Integer.parseInt(string);
        int expectedRemainingShips = defender.getInitialShips() - decrease;
        assertEquals(expectedRemainingShips, defender.getRemainingShips());
    }

    @Given("the ship has {string} remaining hit points")
    public void the_ship_has_remaining_hit_points(String string) {
        int remainingHitPoints = Integer.parseInt(string);
        defender.getBoard().getCell(column, rowNum).getShip().setRemainingHitPoints(remainingHitPoints);
    }

    @Then("the displayed message should not contain {string}")
    public void the_displayed_message_should_not_contain(String string) {
        assertFalse(this.message.contains(string));
    }

    @Given("the player has a ship at {string}, {string} with an orientation of {string}")
    public void the_player_has_a_ship_at_with_an_orientation_of(String col, String row, String orientation) {
        int column = Integer.parseInt(col);
        int rowNum = Integer.parseInt(row);
        Orientation shipOrientation = Orientation.valueOf(orientation);
        Ship ship = new Ship(3, shipOrientation);
        player.placeShip(ship, new Coordinate(column, rowNum));
    }

    @Then("the board should contain the ship at {string}, {string} with an orientation of {string}")
    public void the_board_should_contain_the_ship_at_with_an_orientation_of(String col, String row, String orientation) {
        int column = Integer.parseInt(col);
        int rowNum = Integer.parseInt(row);
        Orientation shipOrientation = Orientation.valueOf(orientation);
        assertEquals(shipOrientation, player.getBoard().getCell(column, rowNum).getShip().getOrientation());
    }

    @When("the player attempts to place a ship at {string}, {string} with an orientation of {string}")
    public void the_player_attempts_to_place_a_ship_at_with_an_orientation_of(String col, String row, String orientation) {
        int column = Integer.parseInt(col);
        int rowNum = Integer.parseInt(row);
        Orientation shipOrientation = Orientation.valueOf(orientation);
        Ship ship = new Ship(3, shipOrientation);
        this.placementSuccessful = player.placeShip(ship, new Coordinate(column, rowNum));
    }

    @Then("the placement should fail")
    public void the_placement_should_fail() {
        assertFalse(placementSuccessful);
    }

    @Then("the board should not be modified")
    public void the_board_should_not_be_modified() {
// Assuming you have a Board method called getShipsCount
        assertEquals(1, player.getBoard().getShipsCount());
    }


     */

}
