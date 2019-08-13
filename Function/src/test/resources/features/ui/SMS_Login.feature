@ui @login

Feature: SMS Login Homepage
This feature verifies the functionality on SMS Login Homepage
 
Scenario: Check that main elements on SMS Homepage are displayed and user can login
When I open SMS Homepage
Then I verify that the page displays Log in link
When I select the Log in link
Then I verify that the SMS Log in page displays UserID field
And the page displays the Password field
#And the page displays Cancel button
And the page displays Login button
#Then I enter login credentials
#And verify the SMS Homepage displays Ok button
#Then I click the OK button
#Then I log out
