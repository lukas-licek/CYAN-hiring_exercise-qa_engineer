Feature: HTTP Status Codes

  Scenario: HTTP 200
    Given nothing
    When HTTP 200 is navigated
    Then the status code is 200

  Scenario: HTTP 301
    Given nothing
    When HTTP 301 is navigated
    Then the status code is 301

  Scenario: HTTP 404
    Given nothing
    When HTTP 404 is navigated
    Then the status code is 404

  Scenario: HTTP 500
    Given nothing
    When HTTP 500 is navigated
    Then the status code is 500