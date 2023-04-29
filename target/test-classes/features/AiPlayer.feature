Feature: AIPlayer functionality
  As a battleship game developer,
  I want to ensure that the AIPlayer class is functioning correctly,
  So that I can manage AI players and their actions in the game.

  Scenario Outline: easy AIPlayer has correct number of remaining ships
    Given a easy AIplayer with "<remaining_ships_count>" remaining ships
    When I retrieve the number of remaining ships of the AIplayer
    Then the number of remaining ships of theAIplayer should be "<remaining_ships_count>"

    Examples:
      | remaining_ships_count |
      | 5                     |
      | 3                     |
      | 0                     |

  Scenario: easy AIPlayer retrieves the board
    Given a easy AIplayer with a board
    When I retrieve the easy AIplayer's board
    Then the board of the AIplayer should not be null

  Scenario Outline: easy AIPlayer handles a shot
    Given a player and a easy AIplayer
    And the easy AIPlayer has a cell at "<col>", "<row>" with a cell status of "<initial_cell_status>"
    When the Player shoots at "<col>", "<row>"
    Then the cell status of easy AIplayer at "<col>", "<row>" should be "<final_cell_status>"

    Examples:
      | col | row | initial_cell_status | final_cell_status |
      | 2   | 3   | OCEAN               | MISSED            |
      | 2   | 3   | SHIP                | HIT               |
      | 2   | 3   | HIT                 | HIT               |
      | 2   | 3   | MISSED              | MISSED            |

  Scenario: easy AIPlayer does not sink a ship
    Given a player and a easy AIplayer
    And the player has a ship at 4, 5 with an orientation of "N" and a type "DESTROYER"
    When the easy AIPlayer shoots
    Then the number of player's remaining ships should stay the same


  Scenario Outline: easy AIPlayer has a ship on board
    Given a easy AIplayer with a board
    And the easy AIplayer has a ship at "<col1>", "<row1>" with an orientation of "<orientation>" and a type "DESTROYER"
    When I retrieve the easy AIplayer's board
    Then the cell status of easy AIplayer at "<col2>", "<row2>" should be "SHIP"
    And the cell status of easy AIplayer at "<col3>", "<row3>" should be "SHIP"

    Examples:
      | col1 | row1 | orientation  | col2  | row2   | col3 | row3 |
      | 2    | 3    | N            | 2     | 3      | 2    | 2    |
      | 4    | 6    | E            | 4     | 6      | 5    | 6    |
      | 9    | 1    | S            | 9     | 1      | 9    | 2    |


  Scenario: easy AIPlayer can place a ship
    Given a easy AIplayer with a board
    When the easy AIplayer attempts to place a ship
    Then the placement of the AiPlayer ship should succeed



  Scenario Outline: hard AIPlayer has correct number of remaining ships
    Given a hard AIplayer with "<remaining_ships_count>" remaining ships
    When I retrieve the number of remaining ships of the AIplayer
    Then the number of remaining ships of theAIplayer should be "<remaining_ships_count>"

    Examples:
      | remaining_ships_count |
      | 5                     |
      | 3                     |
      | 0                     |

  Scenario: hard AIPlayer retrieves the board
    Given a hard AIplayer with a board
    When I retrieve the hard AIplayer's board
    Then the board of the AIplayer should not be null

  Scenario Outline: hard AIPlayer handles a shot
    Given a player and a hard AIplayer
    And the hard AIPlayer has a cell at "<col>", "<row>" with a cell status of "<initial_cell_status>"
    When the Player shoots at "<col>", "<row>"
    Then the cell status of hard AIplayer at "<col>", "<row>" should be "<final_cell_status>"

    Examples:
      | col | row | initial_cell_status | final_cell_status |
      | 2   | 3   | OCEAN               | MISSED            |
      | 2   | 3   | SHIP                | HIT               |
      | 2   | 3   | HIT                 | HIT               |
      | 2   | 3   | MISSED              | MISSED            |

  Scenario: hard AIPlayer does not sink a ship
    Given a player and a hard AIplayer
    And the player has a ship at 4, 5 with an orientation of "N" and a type "DESTROYER"
    When the hard AIPlayer shoots
    Then the number of player's remaining ships should stay the same

  Scenario Outline: hard AIPlayer has a ship on board
    Given a hard AIplayer with a board
    And the hard AIplayer has a ship at "<col1>", "<row1>" with an orientation of "<orientation>" and a type "DESTROYER"
    When I retrieve the hard AIplayer's board
    Then the cell status of hard AIplayer at "<col2>", "<row2>" should be "SHIP"
    And the cell status of hard AIplayer at "<col3>", "<row3>" should be "SHIP"

    Examples:
      | col1 | row1 | orientation  | col2  | row2   | col3 | row3 |
      | 2    | 3    | N            | 2     | 3      | 2    | 2    |
      | 4    | 6    | E            | 4     | 6      | 5    | 6    |
      | 9    | 1    | S            | 9     | 1      | 9    | 2    |


  Scenario: hard AIPlayer can place a ship
    Given a hard AIplayer with a board
    When the hard AIplayer attempts to place a ship
    Then the placement of the AiPlayer ship should succeed

