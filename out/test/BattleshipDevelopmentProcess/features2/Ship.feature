Feature: Ship functionality
  As a battleship game developer,
  I want to ensure that the Ship class is functioning correctly,
  So that I can manage ships and their states in the game.

  Scenario Outline: Ship has correct ship type
    Given a ship with ship type "<ship_type>"
    When I retrieve the ship type
    Then the ship type should be "<ship_type>"

    Examples:
      | ship_type    |
      | CARRIER      |
      | BATTLESHIP   |
      | CRUISER      |
      | SUBMARINE    |
      | DESTROYER    |

  Scenario: Ship has correct list of cells
    Given a ship with a list of cells
    When I retrieve the list of cells
    Then the list of cells should be the same as the initial list

  Scenario Outline: Ship has sunk or not sunk
    Given a ship with a list of cells with cell statuses "<cell_statuses>"
    When I check if the ship has sunk
    Then the result should be "<has_sunk>"

    Examples:
      | cell_statuses      | has_sunk |
      | SHIP,SHIP,SHIP     | false    |
      | HIT,HIT,HIT        | true     |
      | HIT,SHIP,HIT       | false    |
      | HIT,HIT,SHIP       | false    |

  Scenario: Add a cell to the ship
    Given a ship with a list of cells
    When I add a new cell to the list of cells
    Then the list of cells should include the new cell
