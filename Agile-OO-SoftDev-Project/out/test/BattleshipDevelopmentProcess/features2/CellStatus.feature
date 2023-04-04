Feature: Cell status

  Scenario: Check possible cell statuses
    Given the cellStatus enum
    When I check the possible statuses for a cell
    Then I should see O, S, H, and M