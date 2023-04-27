Feature: Coordinates
  As a player
  I want to be able to create coordinates
  So that I can place my ships on the game board

  Scenario: Creating coordinates
    Given a column number of 3
    And a row number of 4
    When I create new Coordinates with these values
    Then the object should have a column number of 3 a row number of 4

  Scenario: Comparing coordinates
    Given two Coordinates objects with the same column and row numbers
    When I check if they are equal
    Then they should be considered equal

    Given two Coordinates objects with different column or row numbers
    When I check if they are equal
    Then they should not be considered equal