@tag
Feature: Play Battleship Game in Graphical Interface
  As a player
  I want to be able to play Battleship game in a graphical interface
  So that I can enjoy the game with visual feedback

  Scenario: successful check-in
    Given a travel card with balance of 100
    And check-in status is false
    And a check-in automaton at "Norreport St."
    When check-in
    Then automaton returns a response message that the card is checked-in