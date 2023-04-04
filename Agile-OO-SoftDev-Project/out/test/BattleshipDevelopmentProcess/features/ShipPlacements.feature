Feature: Battleship Game Ship Placement

  Background:
    Given I am on the game screen
    And a new game has started
    And the game is in multiplayer mode

  Scenario: Orientation Can Have Four Different Orientations
    Given a Ship is created
    Then the Ship should have an orientation of NORTH, SOUTH, EAST or WEST

  Scenario: Place Ship on Game Board for Player 1
    Given it is player 1's turn
    And I have placed less than 5 ships
    When I select a ship to place
    And I select a starting location on the game board
    And I select an Orientation for the ship
    Then the ship should be placed on the game board
    And the game should switch to player 2's turn

  Scenario: Invalid Ship Placement for Player 1
    Given it is player 1's turn
    When I select a ship to place
    And I select an invalid starting location on the game board
    Then I should see an error message
    And the ship should not be placed
    And it should still be player 1's turn

  Scenario: Finish Ship Placement for Player 1
    Given it is player 1's turn
    And I have placed 5 ships
    When I place all of my remaining ships on the game board
    Then the game should switch to player 2's turn

  Scenario: Place Ship on Game Board for Player 2
    Given it is player 2's turn
    And I have placed less than 5 ships
    When I select a ship to place
    And I select a starting location on the game board
    And I select an Orientation for the ship
    Then the ship should be placed on the game board
    And the game should switch to player 1's turn

  Scenario: Invalid Ship Placement for Player 2
    Given it is player 2's turn
    When I select a ship to place
    And I select an invalid starting location on the game board
    Then I should see an error message
    And the ship should not be placed
    And it should still be player 2's turn

  Scenario: Finish Ship Placement for Player 2
    Given it is player 2's turn
    And I have placed 5 ships
    When I place all of my remaining ships on the game board
    Then the game should switch to the attack phase