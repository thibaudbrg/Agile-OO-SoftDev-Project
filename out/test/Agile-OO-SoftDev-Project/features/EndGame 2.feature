Feature: Handle the end of the game

  Scenario: Player 1 Wins
    Given it is player 1's turn
    And I have sunk all of player 2's ships
    Then I should see a message indicating that I have won the game
    And the game should end

  Scenario: Player 2 Wins
    Given it is player 2's turn
    And I have sunk all of player 1's ships
    Then I should see a message indicating that I have won the game
    And the game should end