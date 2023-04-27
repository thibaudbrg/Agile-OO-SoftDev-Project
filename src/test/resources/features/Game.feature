Feature: Battleship Game

  Scenario Outline : Generate game boards with incorrect dimensions
    Given a correct GameMode
    And the game board is generated with numCol of <numCol> and numRow of <numRow>
    Then a RuntimeException is thrown when we create the game

    Examples:
      | numCol | numRow |
      | -1     | 3      |
      | 2      | -3     |
      | 25     | 3      |
      | 11     | 28     |



  Scenario: Generate players with incorrect mode
    Given correct dimensions
    And a null mode
    Then a RuntimeException is thrown when we create the game

  Scenario Outline: Generate Game with correct mode and correct dimensions
    Given correct dimensions
    And a correct "<GameMode>"
    Then no RuntimeException is thrown when we create the game

    Examples:
      | GameMode       |
      | EASY           |
      | HARD           |
      | MULTIPLAYER    |