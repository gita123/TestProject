@ui @coscas
Feature: CosCas Checker
  This feature logs into the CosCas Checker and does a search

  Scenario: Perform a CosCas Check

    When I open CosCas Check Homepage
    And Enter the UserID and Password
    And Click on the LogIn Button
    And Search for a Cos Number
    And Check correct Cos Result Returned
    And I Click on the Back Button
    And I Click on the Cas Link
    And Retrieve a Cas Number
    And Check Correct Cas Result Returned
    And Click on Logout Link




