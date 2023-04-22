Feature: Board

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
    Then all cells on the board should have status OCEAN or ROCK

  Scenario: Fill board with cells in correct positions
    Given a board with size 5 x 5
    When the board is filled with cells
    Then the cell at position (2;3) should have status OCEAN
    And the cell at position (4;1) should have status OCEAN