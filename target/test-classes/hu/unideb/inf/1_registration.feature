Feature: Way2Automation registration

  Background:
    Given The Registration Form page is open

  Scenario Outline:
    Given The Registration Form page is open
    And The Name is filled with '<name>'
    And The Phone is filled with '<phone>'
    And The Email is filled with '<email>'
    And The selected Country is '<country>'
    And The City is filled with '<city>'
    And The Username is filled with '<username>'
    And The Password is filled with '<password>'
    And The Submit button is clicked
    Then The '<field>' shows the '<msg>'
    Examples:
      | name        | phone           | email                  | country   | city       | username      | password  | field     | msg                                                                         |
      |             |                 |                        |           |            |               |           | name      | Please fill out this field.                                                 |
      | John Smith  |                 |                        |           |            |               |           | phone     | Please fill out this field.                                                 |
      | Jacob Smith | +36 12 345 6789 |                        |           |            |               |           | email     | Please fill out this field.                                                 |
      #| Jack Smith  | +36 12 345 6788 | wrongemail             |           |            |               |           | email     | Please include an '@' in the email address. 'wrongemail' is missing an '@'. |
      | Jeff Smith  | +36 12 345 6787 | crrect_Test1@gmail.com | Hungary   |            |               |           | city      | Please fill out this field.                                                 |
      | Jill Smith  | +36 12 345 6786 | crrect_Test2@gmail.com | Hong Kong | Shoji      |               |           | username  | Please fill out this field.                                                 |
      | Jenna Smith | +36 12 345 6785 | crrect_Test3@gmail.com | Greece    | Agreekcity | Jennasmoth123 |           | password  | Please fill out this field.                                                 |
      | Jenna Smit2 | +36 12 345 6785 | crrect_Test3@gmail.com | France    | Paris      | Jennasmoth987 | password1 | password  |                                                                             |
