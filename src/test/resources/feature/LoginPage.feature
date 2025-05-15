Feature: Testing Login Page

  Scenario: Sign in with valid credentials
    Given the user is on the login page
    When the user enters login email "registrarfocean@gmail.com"
    And the user enters login password "bpNANETAq5xvPrG"
    And the user clicks the Sign In button
    Then the user should be successfully signed in

  Scenario: Sign in with invalid password
    Given the user is on the login page
    When the user enters login email "registrarfocean@gmail.com"
    And the user enters login password "bpN!!ANETAq5xvPr"
    And the user clicks the Sign In button
    Then the user should see an 'Invalid email or password' error message