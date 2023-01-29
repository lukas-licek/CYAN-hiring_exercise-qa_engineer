Feature: REST API JSON

  Scenario: REST API JSON
    Given nothing
    When user page is opened
    Then the response contains user with address
