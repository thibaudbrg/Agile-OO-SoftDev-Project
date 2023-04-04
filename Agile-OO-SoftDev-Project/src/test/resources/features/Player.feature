Feature: Player

  Scenario: Initializing Players
    Given two players, PLAYER_1 and PLAYER_2
    When the game starts
    Then a player is randomly selected to play first
    And the other player is the next player

  Scenario: Switching Players
    Given two players, PLAYER_1 and PLAYER_2
    When the current player is PLAYER_1
    And the next player is called
    Then PLAYER_2 becomes the current player

  Scenario: Switching Players Again
    Given two players, PLAYER_1 and PLAYER_2
    When the current player is PLAYER_2
    And the next player is called
    Then PLAYER_1 becomes the current player

  Scenario: Check remaining ships for a player
    Given a player with a set of ships on their board
    When I request the number of remaining ships for that player
    Then the system should return the number of remaining ships

  Scenario: Player 1 is Playing and Player 2 Cannot Play
    Given I am on the game screen
    And a new game has started
    When it is Player 1's turn to play
    Then Player 2 should not be able to make any moves
    And the game should display a message that it is Player 1's turn to play

  Scenario: Player 2 is Playing and Player 1 Cannot Play
    Given I am on the game screen
    And a new game has started
    When it is Player 2's turn to play
    Then Player 1 should not be able to make any moves
    And the game should display a message that it is Player 2's turn to play