Feature: Orientation

  Scenario: Check possible orientations
    Given the Orientation enum
    When I check the possible orientations for a ship
    Then I should see N,S,E,W