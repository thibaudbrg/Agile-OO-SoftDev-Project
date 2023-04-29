import io.cucumber.java.en.*;
import battleship.game.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class AiPlayerTest {

    private int numberOfRemainingShips;
    private int beforeActionPlayer2RemainingShip;
    private Board board;
    private AIPlayer aiPlayer1;
    private RealPlayer realPlayer2;
    private boolean playerPlacesWorked;

    @Given("a easy AIplayer with {string} remaining ships")
    public void a_easy_a_iplayer_with_remaining_ships(String string) {
        List<Ship> remainingShips = new ArrayList<>(Integer.parseInt(string));
        for (int i = 0; i < Integer.parseInt(string); i++) {
            remainingShips.add(new Ship(new ArrayList<>(), ShipType.BATTLESHIP));
        }
        board = new Board(10, 10,false);
        this.aiPlayer1 = new AIPlayer(remainingShips, board,PlayerId.PLAYER_2,false);

    }

    @When("I retrieve the number of remaining ships of the AIplayer")
    public void i_retrieve_the_number_of_remaining_ships_of_the_a_iplayer() {
        numberOfRemainingShips = aiPlayer1.getNumberOfRemainingShips();
    }

    @Then("the number of remaining ships of theAIplayer should be {string}")
    public void the_number_of_remaining_ships_of_the_a_iplayer_should_be(String string) {
        int expectedRemainingShips = Integer.parseInt(string);
        assertEquals(numberOfRemainingShips,expectedRemainingShips);
    }

    @Given("a easy AIplayer with a board")
    public void a_easy_a_iplayer_with_a_board() {
        List<Ship> remainingShips = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            remainingShips.add(new Ship(new ArrayList<>(), ShipType.BATTLESHIP));
        }
        board = new Board(10, 10,false);
        this.aiPlayer1 = new AIPlayer(remainingShips, board,PlayerId.PLAYER_1,false);
    }



    @When("I retrieve the easy AIplayer's board")
    public void i_retrieve_the_easy_a_iplayer_s_board() { this.board = aiPlayer1.getBoard();}

    @Then("the board of the AIplayer should not be null")
    public void the_board_of_the_a_iplayer_should_not_be_null() {
        assertNotNull(board);
    }

    @Given("a player and a easy AIplayer")
    public void a_player_and_a_easy_a_iplayer() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(new Coordinates(2, 2), CellStatus.SHIP));
        cells.add(new Cell(new Coordinates(2, 3), CellStatus.SHIP));
        Board board1 = new Board(10, 10,false);
        List<Ship> remainingShips1 = new ArrayList<>();

        remainingShips1.add(new Ship(cells, ShipType.DESTROYER));

        aiPlayer1 = new AIPlayer(remainingShips1, board1,PlayerId.PLAYER_2, false);

        Board board2 = new Board(10, 10,false);
        List<Ship> remainingShips2 = new ArrayList<>();

        remainingShips2.add(new Ship(cells, ShipType.DESTROYER));

        realPlayer2 = new RealPlayer(remainingShips2, board2,PlayerId.PLAYER_1);
    }

    @Given("the easy AIPlayer has a cell at {string}, {string} with a cell status of {string}")
    public void the_easy_ai_player_has_a_cell_at_with_a_cell_status_of(String col, String row, String status) {
        aiPlayer1.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).setCellStatus(CellStatus.valueOf(status));
    }

    @When("the Player shoots at {string}, {string}")
    public void the_player_shoots_at(String arg0, String arg1) {
        realPlayer2.handleShot(new Coordinates(Integer.parseInt(arg0), Integer.parseInt(arg1)), aiPlayer1);
    }



    @Then("the cell status of easy AIplayer at {string}, {string} should be {string}")
    public void the_cell_status_of_easy_a_iplayer_at_should_be(String col, String row, String arg2) {
        CellStatus status = aiPlayer1.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).getCellStatus();
        assertEquals(status, CellStatus.valueOf(arg2));

    }

    @Given("the player has a ship at {int}, {int} with an orientation of {string} and a type {string}")
    public void the_player_has_a_ship_at_with_an_orientation_of_and_a_type(Integer i1, Integer i2, String o, String st) {
        realPlayer2.addShip(ShipType.valueOf(st),new Coordinates(i1, i2), Orientation.valueOf(o));
        beforeActionPlayer2RemainingShip = realPlayer2.getNumberOfRemainingShips();
    }

    @When("the easy AIPlayer shoots")
    public void the_easy_ai_player_shoots() {
        Coordinates aiCoords = ((AIPlayer) aiPlayer1).getAICoordinates();
        aiPlayer1.handleShot(aiCoords,realPlayer2);

    }

    @Then("the number of player's remaining ships should stay the same")
    public void the_number_of_player_s_remaining_ships_should_stay_the_same() {
        assertEquals(beforeActionPlayer2RemainingShip, realPlayer2.getNumberOfRemainingShips());
    }

    @Given("the easy AIplayer has a ship at {string}, {string} with an orientation of {string} and a type {string}")
    public void the_easy_a_iplayer_has_a_ship_at_with_an_orientation_of_and_a_type(String i1, String i2, String o, String st) {
        Ship ship = new Ship(new ArrayList<>(), ShipType.valueOf(st));
        aiPlayer1.addShip(ShipType.valueOf(st),new Coordinates(Integer.parseInt(i1), Integer.parseInt(i2)), Orientation.valueOf(o));
        aiPlayer1.getRemainingShips().add(ship);
    }

    @When("the easy AIplayer attempts to place a ship")
    public void the_easy_a_iplayer_attempts_to_place_a_ship() {
        playerPlacesWorked = aiPlayer1.addShip(ShipType.CARRIER);
    }

    @Given("a hard AIplayer with {string} remaining ships")
    public void a_hard_a_iplayer_with_remaining_ships(String string) {
        List<Ship> remainingShips = new ArrayList<>(Integer.parseInt(string));
        for (int i = 0; i < Integer.parseInt(string); i++) {
            remainingShips.add(new Ship(new ArrayList<>(), ShipType.BATTLESHIP));
        }
        board = new Board(10, 10,false);
        this.aiPlayer1 = new AIPlayer(remainingShips, board,PlayerId.PLAYER_2,true);
    }

    @Given("a hard AIplayer with a board")
    public void a_hard_a_iplayer_with_a_board() {
        List<Ship> remainingShips = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            remainingShips.add(new Ship(new ArrayList<>(), ShipType.BATTLESHIP));
        }
        board = new Board(10, 10,false);
        this.aiPlayer1 = new AIPlayer(remainingShips, board,PlayerId.PLAYER_1,true);

    }

    @When("I retrieve the hard AIplayer's board")
    public void i_retrieve_the_hard_a_iplayer_s_board() {
        this.board = aiPlayer1.getBoard();
    }

    @Given("a player and a hard AIplayer")
    public void a_player_and_a_hard_a_iplayer() {
        List<Cell> cells = new ArrayList<>();
        cells.add(new Cell(new Coordinates(2, 2), CellStatus.SHIP));
        cells.add(new Cell(new Coordinates(2, 3), CellStatus.SHIP));
        Board board1 = new Board(10, 10,false);
        List<Ship> remainingShips1 = new ArrayList<>();

        remainingShips1.add(new Ship(cells, ShipType.DESTROYER));

        aiPlayer1 = new AIPlayer(remainingShips1, board1,PlayerId.PLAYER_1, true);

        Board board2 = new Board(10, 10,false);
        List<Ship> remainingShips2 = new ArrayList<>();

        remainingShips2.add(new Ship(cells, ShipType.DESTROYER));

        realPlayer2 = new RealPlayer(remainingShips2, board2,PlayerId.PLAYER_1);

    }

    @Given("the hard AIPlayer has a cell at {string}, {string} with a cell status of {string}")
    public void the_hard_ai_player_has_a_cell_at_with_a_cell_status_of(String col, String row, String status) {
        aiPlayer1.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).setCellStatus(CellStatus.valueOf(status));
    }

    @Then("the cell status of hard AIplayer at {string}, {string} should be {string}")
    public void the_cell_status_of_hard_a_iplayer_at_should_be(String col, String row, String arg2) {
        CellStatus status = aiPlayer1.getBoard().getCell(new Coordinates(Integer.parseInt(col), Integer.parseInt(row))).getCellStatus();
        assertEquals(status, CellStatus.valueOf(arg2));

    }

    @When("the hard AIPlayer shoots")
    public void the_hard_ai_player_shoots() {
        Coordinates aiCoords = ((AIPlayer) aiPlayer1).getAICoordinates();
        aiPlayer1.handleShot(aiCoords,realPlayer2);
    }

    @Given("the hard AIplayer has a ship at {string}, {string} with an orientation of {string} and a type {string}")
    public void the_hard_a_iplayer_has_a_ship_at_with_an_orientation_of_and_a_type(String i1, String i2, String o, String st) {
        Ship ship = new Ship(new ArrayList<>(), ShipType.valueOf(st));
        aiPlayer1.addShip(ShipType.valueOf(st),new Coordinates(Integer.parseInt(i1), Integer.parseInt(i2)), Orientation.valueOf(o));
        aiPlayer1.getRemainingShips().add(ship);
    }

    @When("the hard AIplayer attempts to place a ship")
    public void the_hard_a_iplayer_attempts_to_place_a_ship() {
        playerPlacesWorked = aiPlayer1.addShip(ShipType.CARRIER);
    }

    @Then("the placement of the AiPlayer ship should succeed")
    public void the_placement_of_the_ai_player_ship_should_succeed() {
        assertTrue(playerPlacesWorked);
    }

}
