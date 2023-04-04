Feature: Cell

  Scenario: Create a cell with correct parameters
    Given a column value of 3, row value of 5 and status of OCEAN
    When a cell is created with these parameters
    Then the cell's column value should be 3
    And the cell's row value should be 5
    And the cell's status should be OCEAN

  Scenario: Change a cell's status
    Given a cell with status of OCEAN
    When the cell's status is changed to SHIP
    Then the cell's status should be SHIP

  Scenario: Get a cell's column and row value
    Given a cell with column value of 4 and row value of 2
    When the cell's column and row value is retrieved
    Then the column value should be 4
    And the row value should be 2

  Scenario: Get a cell's status
    Given a cell with status of SHIP
    When the cell's status is retrieved
    Then the status should be SHIP
