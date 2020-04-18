Feature: Way2Automation login

  Background:
    Given  The Registration Form page is open
    And Sign in link is pressed

  Scenario:
    Given Valid username is written into username field
    And Invalid password is written into password field
    And The Submit button is clicked
    Then Incorrect Username or Password message appears

  Scenario:
    Given Valid username is written into username field
    And Valid password is written into password field
    And The Submit button is clicked
    Then The Login window dissapears
