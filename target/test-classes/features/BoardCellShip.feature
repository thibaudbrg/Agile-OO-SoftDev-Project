Feature: Board, Cell, and Ship Classes

  Background:
    Given a Board is created
    And a Ship is created

  Scenario: Cell Can Have Four Different Status
    Given a Cell is created
    Then the Cell should have a status of OCEAN
    And the Cell status can be changed to SHIP, HIT, or MISS

  Scenario: Board is Made of an Array of Cells
    Given the Board is created
    Then the Board should be an array of Cells
    And each Cell should have a specific column number, a row number and a status

  Scenario: Ship is an Array of Cells
    Given the Ship is created
    Then the Ship should be an array of Cells
    And each Ship should be disposable on the Board

  Scenario: Ships can be different types
    Given the following ship types exist:
      | Type       | Length | Image                   |
      | CARRIER    | 5      | "assets/carrier.png"    |
      | BATTLESHIP | 4      | "assets/battleship.png" |
      | CRUISER    | 3      | "assets/cruiser.png"    |
      | SUBMARINE  | 3      | "assets/submarine.png"  |
      | DESTROYER  | 2      | "assets/destroyer.png"  |
    Then each ship type should have a corresponding length and image.

  Scenario: Placing a Ship on Board Changes Cell State
    Given the Board is created
    And the Ship is created
    When the Ship is placed on the Board
    Then the state of the Cells where the Ship is placed should change from OCEAN to SHIP

