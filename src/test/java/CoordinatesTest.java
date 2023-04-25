import battleship.game.Coordinates;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;


public class CoordinatesTest {
    int columnNumber ;
    int rowNumber ;

    Coordinates coordinates1 ;

    Coordinates coordinates2 ;

    boolean areEqual ;

    @Given("a column number of {int}")
    public void a_column_number_of(Integer int1) {
        columnNumber = int1;
    }
    @Given("a row number of {int}")
    public void a_row_number_of(Integer int1) {
        rowNumber = int1;
    }
    @When("I create new Coordinates with these values")
    public void i_create_new_coordinates_with_these_values() {
        coordinates1 = new Coordinates(columnNumber,rowNumber);
    }
    @Then("the object should have a column number of {int} a row number of {int}")
    public void the_object_should_have_a_column_number_of_a_row_number_of(Integer col, Integer row) {
        assertTrue(coordinates1.getCol()==col && coordinates1.getRow()==row);
    }

    @Given("two Coordinates objects with the same column and row numbers")
    public void two_coordinates_objects_with_the_same_column_and_row_numbers() {
        coordinates1= new Coordinates(3,4);
        coordinates2= new Coordinates(3,4);

    }
    @When("I check if they are equal")
    public void i_check_if_they_are_equal() {
        areEqual = coordinates1.equals(coordinates2);
    }
    @Then("they should be considered equal")
    public void they_should_be_considered_equal() {
        assertTrue(areEqual);
    }
    @Given("two Coordinates objects with different column or row numbers")
    public void two_coordinates_objects_with_different_column_or_row_numbers() {
        coordinates1= new Coordinates(3,4);
        coordinates2= new Coordinates(2,4);
    }
    @Then("they should not be considered equal")
    public void they_should_not_be_considered_equal() {
        assertFalse(areEqual);
    }
}
