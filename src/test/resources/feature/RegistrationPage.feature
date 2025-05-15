Feature: Testing Registration Page

  Scenario: Register with valid inputs
    Given the user is on the registration page
    When the user enters first name "Jonnny"
    And the user enters last name "Kendrick"
    And the user enters date of birth "1990-01-01"
    And the user enters registration email "<random>"
    And the user enters registration password "bpNANETAq5xvPrG"
    And the user confirms the password "bpNANETAq5xvPrG"
    And the user clicks the Submit button
    Then the user should be successfully registered

  Scenario: User tries to register with missing required fields
    Given the user is on the registration page
    When the user enters first name ""
    And the user enters registration email "registrarffocean@gmail.com"
    And the user enters registration password "bpNANETAq5xvPrG"
    And the user confirms the password "bpNANETAq5xvPrG"
    Then the required field error message should be displayed