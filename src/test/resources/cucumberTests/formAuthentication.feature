Feature: Form Authentication

  Scenario: Form Authentication Successful
    Given nothing
    When the login credentials are correct
    Then the login is successful

  Scenario: Form Authentication Rejected
    Given nothing
    When the login credentials are incorrect
    Then the login is rejected
