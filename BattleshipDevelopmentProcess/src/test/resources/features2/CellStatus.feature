Feature: Cell status

  Scenario: Check possible cell statuses
    Given I have a cell at position (2;2)
    When I check the possible statuses for a cell
    Then I should see O, S, H, and M