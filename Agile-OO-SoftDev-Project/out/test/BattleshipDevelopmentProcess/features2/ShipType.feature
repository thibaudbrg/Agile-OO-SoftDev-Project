Feature: ShipType enum functionality
  As a battleship game developer,
  I want to ensure that the ShipType enum is functioning correctly,
  So that I can manage ship types and their sizes in the game.

  Scenario Outline: ShipType has correct label
    Given a ShipType "<ship_type>"
    When I retrieve the label of the ship type
    Then the label should be "<label>"

    Examples:
      | ship_type  | label |
      | CARRIER    | 5     |
      | BATTLESHIP | 4     |
      | CRUISER    | 3     |
      | SUBMARINE  | 3     |
      | DESTROYER  | 2     |