Feature: Battleship Game Attack Phase

  Background:
    Given I am on the game screen
    And a new game has started
    And both players have placed their ships

  Scenario: Player 1 Takes Shot
    Given it is player 1's turn
    When I select a location on the opponent's game board
    Then the game should indicate whether I hit or missed a ship
    And the corresponding cell's status should be updated
    And the game should switch to player 2's turn

  Scenario: Player 1 Invalid Shot
    Given it is player 1's turn
    When I select an invalid location on the opponent's game board
    Then I should see an error message
    And it should still be player 1's turn

  Scenario: Player 2 Takes Shot
    Given it is player 2's turn
    When I select a location on the opponent's game board
    Then the game should indicate whether I hit or missed a ship
    And the corresponding cell's status should be updated
    And the game should switch to player 1's turn

  Scenario: Player 2 Invalid Shot
    Given it is player 2's turn
    When I select an invalid location on the opponent's game board
    Then I should see an error message
    And it should still be player 2's turn

  Scenario: Player 1 Sinks a Ship
    Given it is player 1's turn
    And I have hit a ship
    And the ship has been sunk
    Then I should see a message indicating the ship has been sunk
    And the corresponding cell's status should be updated
    And the game should switch to player 2's turn

  Scenario: Player 2 Sinks a Ship
    Given it is player 2's turn
    And I have hit a ship
    And the ship has been sunk
    Then I should see a message indicating the ship has been sunk
    And the corresponding cell's status should be updated
    And the game should switch to player 1's turn

