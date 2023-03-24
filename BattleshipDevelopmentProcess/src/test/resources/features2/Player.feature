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
    Then the cell status at "<col>", "<row>" should be "<final_cell_status>"
    And the displayed message should be "<message>"

    Examples:
      | col | row | initial_cell_status | final_cell_status | message          |
      | 2   | 3   | OCEAN               | MISSED            | miss !           |
      | 2   | 3   | SHIP                | HIT               | HIT !            |
      | 2   | 3   | HIT                 | HIT               | already Hit      |
      | 2   | 3   | MISSED              | MISSED            | already missed ! |

  Scenario: Player sinks a ship
    Given two players, Player_1 and Player_2
    And the Player 1 has a ship at "4", "5" with a cell status of "SHIP"
    And the ship has "1" remaining hit point
    When Player_2 shoots at "4", "5"
    Then the cell status should be "HIT"
    And the displayed message should contain "one ship has sunk"
    And the number of Defender's remaining ships should decrease by "1"


  Scenario: Player does not sink a ship
    Given two players , Player_1 and Player_2
    And Player 1 has a ship at "6", "7" with a cell status of "SHIP"
    And the ship has "2" remaining hit points
    When Player 2  shoots at "6", "7"
    Then the cell status should be "HIT"
    And the displayed message should not contain "one ship has sunk"

  Scenario Outline: Player has a ship on board
    Given a player with a board
    And the player has a ship at "<col>", "<row>" with an orientation of "<orientation>"
    When I retrieve the player's board
    Then the board should contain the ship at "<col>", "<row>" with an orientation of "<orientation>"

    Examples:
      | col | row | orientation |
      | 2   | 3   | N           |
      | 4   | 6   | E           |
      | 9   | 1   | S           |

  Scenario: Player cannot place overlapping ships
    Given a player with a board
    And the player has a ship at "2", "3" with an orientation of "N"
    When the player attempts to place a ship at "2", "2" with an orientation of "E"
    Then the placement should fail
    And the board should not be modified