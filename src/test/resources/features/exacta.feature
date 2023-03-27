Feature: Exacta search
  @web
  Scenario: Search for Outsource
    Given the user navigates to "https://www.hexacta.com/"
    When the user searches for "Outsource"
    Then the user validates that at least one of the results contains the text "Outsourced software team"
  @ws
  Scenario: Consuming Web Services
    Given The user consumes the web services to get the post with id "1"
    Then the user validate that the response contains the correct title of the post
