Feature: Information Box Dialog

  Scenario: Player Receives Information Box Dialogue
    Given I am on the game screen
    And a new game has started
    When it is Player 1's turn to place their ships
    Then an info box dialog should display the message "Player 1, you need to place your ships on the board"
    And the dialog should display the player's name

    When Player 1 will play first
    Then an info box dialog should display the message "Player 1 will play first."
    And the dialog should display the player's name

    When Player 1 has placed their ships
    Then an info box dialog should display the message "Player 1 has placed his ships."
    And the dialog should display the player's name

    When it is Player 1's turn to play
    Then an info box dialog should display the message "It's Player 1's turn."
    And the dialog should display the player's name

    When Player 1 hits a ship
    Then an info box dialog should display the message "Player 1 hit!"
    And the dialog should display the player's name

    When Player 1 misses a ship
    Then an info box dialog should display the message "Player 1 missed..."
    And the dialog should display the player's name

    When Player 1 sinks a ship
    Then an info box dialog should display the message "Player 1 sank the Player 1."
    And the dialog should display the player's name
    And the dialog should display the name of the ship that was sunk

    When Player 1 wins the game
    Then an info box dialog should display the message "\nPlayer 1 won the game!"
    And the dialog should display the player's name

    Given I am on the game screen
    And a new game has started
    When it is Player 2's turn to place their ships
    Then an info box dialog should display the message "Player 2, you need to place your ships on the board"
    And the dialog should display the player's name

    When Player 2 will play first
    Then an info box dialog should display the message "Player 2 will play first."
    And the dialog should display the player's name

    When Player 2 has placed their ships
    Then an info box dialog should display the message "Player 2 has placed his ships."
    And the dialog should display the player's name

    When it is Player 2's turn to play
    Then an info box dialog should display the message "It's Player 2's turn."
    And the dialog should display the player's name

    When Player 2 hits a ship
    Then an info box dialog should display the message "Player 2 hit!"
    And the dialog should display the player's name

    When Player 2 misses a ship
    Then an info box dialog should display the message "Player 2 missed..."
    And the dialog should display the player's name

    When Player 2 sinks a ship
    Then an info box dialog should display the message "Player 2 sank the Player 2."
    And the dialog should display the player's name
    And the dialog should display the name of the ship that was sunk

    When Player 2 wins the game
    Then an info box dialog should display the message "\nPlayer 2 won the game!"
    And the dialog should display the player's name