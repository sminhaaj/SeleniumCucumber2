Feature: Test Login functionalities

  @positive_test
  Scenario Outline: Check login is successful with valid credentials
    Given User is on the login page
    When  User enters username "<username>" and password "<password>"
    And   User clicks the login button
    Then  User is navigated to home page

    Examples:
    |username| password  |
    |student |Password123|

  @negative_test
  Scenario Outline: Check login is unsuccessful with invalid username
    Given User is on the login page
    When  User enters username "<username>" and password "<password>"
    And   User clicks the login button
    Then  User is failed to login for invalid username

    Examples:
      |username | password  |
      |student1 |Password123|
      |studenT  |Password123|

  @negative_test
  Scenario Outline: Check login is unsuccessful with invalid password
    Given User is on the login page
    When  User enters username "<username>" and password "<password>"
    And   User clicks the login button
    Then  User is failed to login for invalid password

    Examples:
      |username | password  |
      |student  |Password12 |