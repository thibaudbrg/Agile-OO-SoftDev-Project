Feature: GameProgression

  Scenario: Check possible GameProgression
    Given the GameProgression enum
    When I check the possible GameProgressions
    Then I should see SHIP_PLACEMENT_PLAYER_1, SHIP_PLACEMENT_PLAYER_2 and PLAYING_GAME

  Scenario Outline: Check possible GameProgression
    Given <numb> placed ships
    When I check the gameProgression
    Then It should be "<progression>"

    Examples:
      | numb | progression              |
      | 3    | SHIP_PLACEMENT_PLAYER_1  |
      | 8    | SHIP_PLACEMENT_PLAYER_2  |
      | 10   | PLAYING_GAME             |


  Scenario Outline: Check that all ships are placed for a given player
    Given <numb> placed ships
    And the player <id>
    When we check if all its ships are placed
    Then It should be the boolean "<boolean>"

    Examples:
      | numb | id  | boolean |
      | 10   | 2   | true    |
      | 3    | 1   | false   |
      | 8    | 1   | false   |
      | 5    | 1   | true    |
      | 3    | 2   | false   |
      | 12   | 2   | false   |




