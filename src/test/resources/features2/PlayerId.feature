Feature: PlayerId enum functionality
  As a battleship game developer,
  I want to ensure that the PlayerId enum is functioning correctly,
  So that I can manage player turns and identities in the game.

  Scenario: PlayerId has correct count
    Given the PlayerId enum
    When I retrieve the count of player IDs
    Then the count should be "2"

  Scenario Outline: PlayerId has correct next player
    Given a PlayerId "<current_player>"
    When I retrieve the next player
    Then the next player should be "<next_player>"

    Examples:
      | current_player | next_player |
      | PLAYER_1       | PLAYER_2    |
      | PLAYER_2       | PLAYER_1    |

  Scenario: PlayerId has all player IDs
    Given the PlayerId enum
    When I retrieve all player IDs
    Then the list of player IDs should contain "PLAYER_1" and "PLAYER_2"
