Feature: Board

# Adding a ship to the board

  Scenario: Successfully add a ship to the board
    Given a board with size 10 x 10
    When a ship of size 3 is added at cell (2;3) with orientation East
    Then the cells (2;3), (3;3) and (4;3) should have status SHIP

  Scenario: Add a ship that collides with another ship
    Given a board with size 10 x 10
    And a ship already placed at (2;3) to (4;3)
    When a ship of size 4 is added at cell (3;3) with orientation South
    Then the ship should not be added

  Scenario: Add a ship that is out of the board
    Given a board with size 10 x 10
    When a ship of size 3 is added at cell (0;0) with orientation North
    Then the ship should not be added

# Accessing cells on the board

  Scenario: Get cell at specific location
    Given a board with size 8 x 8
    When the cell at position (4;5) is accessed
    Then the returned cell should have coordinates (4;5)

  Scenario: Get size of the board
    Given a board with size 12 x 12
    When the size of the board is requested
    Then the returned size should be 12 x 12

# Filling the board with cells

  Scenario: Fill board with correct number of cells
    Given a board with size 6 x 8
    When the board is filled with cells
    Then the number of cells on the board should be 48

  Scenario: Fill board with ocean cells
    Given a board with size 10 x 10
    When the board is filled with cells
    Then all cells on the board should have status OCEAN

  Scenario: Fill board with cells in correct positions
    Given a board with size 5 x 5
    When the board is filled with cells
    Then the cell at position (2;3) should have status OCEAN
    And the cell at position (4;1) should have status OCEAN