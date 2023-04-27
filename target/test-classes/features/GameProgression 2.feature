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


