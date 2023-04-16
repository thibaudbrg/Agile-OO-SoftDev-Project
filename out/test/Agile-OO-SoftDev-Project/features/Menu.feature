Feature: Battleship Menu Graphical Interface

  Scenario: Display Main Menu
    Given I have launched the Battleship game
    When the main menu is displayed
    Then I should see three buttons: start, help, and exit

  Scenario: Click Start Button
    Given I am on the main menu
    When I click the start button
    Then a new pane should be displayed
    And the pane should have three buttons: multiplayer, easy mode, and hard mode

  Scenario: Click Multiplayer Button
    Given I am on the start pane
    When I click the multiplayer button
    Then the game should start in multiplayer mode

  Scenario: Click Easy Mode Button
    Given I am on the start pane
    When I click the easy mode button
    Then the game should start in easy mode

  Scenario: Click Hard Mode Button
    Given I am on the start pane
    When I click the hard mode button
    Then the game should start in hard mode

  Scenario: Click Rules Button
    Given I am on the main menu
    When I click the rules button
    Then a new pane should be displayed
    And the pane should display the rules of the game

  Scenario: Click Exit Button
    Given I am on the main menu
    When I click the exit button
    Then the game should quit
    And the game window should close

  Scenario: Click Red Cross to Quit Game
    Given I am on the game screen
    When I click the red cross in the top right corner
    Then a pop-up tab should be displayed
    And the tab should ask if I really want to quit the game
    And there should be two buttons: yes and cancel

  Scenario: Click Yes to Quit Game
    Given a pop-up tab is displayed
    When I click the yes button
    Then the game should quit
    And the game window should close

  Scenario: Click Cancel to Continue Game
    Given a pop-up tab is displayed
    When I click the cancel button
    Then the pop-up tab should close
    And the game should continue