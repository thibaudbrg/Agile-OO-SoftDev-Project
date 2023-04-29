Feature: Player functionality
  As a battleship game developer,
  I want to ensure that the Player class is functioning correctly,
  So that I can manage players and their actions in the game.

  Scenario Outline: Player has correct number of remaining ships
    Given a player with "<remaining_ships_count>" remaining ships
    When I retrieve the number of remaining ships
    Then the number of remaining ships should be "<remaining_ships_count>"

    Examples:
      | remaining_ships_count |
      | 5                     |
      | 3                     |
      | 0                     |

  Scenario: Player retrieves the board
    Given a player with a board
    When I retrieve the player's board
    Then the board should not be null

  Scenario Outline: Player handles a shot
    Given two players
    And the first Player has a cell at "<col>", "<row>" with a cell status of "<initial_cell_status>"
    When the second Player shoots at "<col>", "<row>"
    Then the cell status of first player at "<col>", "<row>" should be "<final_cell_status>"

    Examples:
      | col | row | initial_cell_status | final_cell_status |
      | 2   | 3   | OCEAN               | MISSED            |
      | 2   | 3   | SHIP                | HIT               |
      | 2   | 3   | HIT                 | HIT               |
      | 2   | 3   | MISSED              | MISSED            |

  Scenario: Player does not sink a ship
    Given two players
    And the first player has a ship at 4, 5 with an orientation of "N" and a type "DESTROYER"
    When the second Player shoots at "4", "5"
    Then the cell status of first player at "4", "5" should be "HIT"
    And the number of first player's remaining ships should stay the same


  Scenario: Player sinks a ship
    Given two players
    And the first player has a ship at 4, 5 with an orientation of "N" and a type "DESTROYER"
    And the the second Player shoots at "4", "5"
    When the second Player shoots at "4", "4"
    Then the number of first player's remaining ships should decrease by 1

  Scenario Outline: Player has a ship on board
    Given a player with a board
    And the player has a ship at "<col1>", "<row1>" with an orientation of "<orientation>" and a type "DESTROYER"
    When I retrieve the player's board
    Then the cell status of player at "<col2>", "<row2>" should be "SHIP"
    And the cell status of player at "<col3>", "<row3>" should be "SHIP"

    Examples:
      | col1 | row1 | orientation  | col2  | row2   | col3 | row3 |
      | 2    | 3    | N            | 2     | 3      | 2    | 2    |
      | 4    | 6    | E            | 4     | 6      | 5    | 6    |
      | 9    | 1    | S            | 9     | 1      | 9    | 2    |


  Scenario: Player can place a ship
    Given a player with a board
    When the player attempts to place a ship at "0", "0" with an orientation of "S"
    Then the placement should succeed


  Scenario: Player cannot place overlapping ships
    Given a player with a board
    And the player has a ship at "2", "3" with an orientation of "N" and a type "DESTROYER"
    When the player attempts to place a ship at "2", "2" with an orientation of "E"
    Then the placement should fail

  Scenario: Player cannot place ships outside the board
    Given a player with a board
    When the player attempts to place a ship at "0", "0" with an orientation of "N"
    Then the placement should fail

  Scenario: Player cannot place ships hitting a Rock
    Given a player with a board
    And the player's Board has a Rock at "0", "1"
    When the player attempts to place a ship at "0", "0" with an orientation of "N"
    Then the placement should fail

  Scenario: Player gets a bomb
    Given two players
    And the second Player has a Rock at 0 , 0
    When the first player shoots at 0 , 0
    Then the second Player must have a bomb


  Scenario: Player shoots a bomb
    Given two players
    And the first player has a bomb
    When the first player shoots at 2 , 2
    Then the neighboring cells of 2, 2 have a status different from ocean


