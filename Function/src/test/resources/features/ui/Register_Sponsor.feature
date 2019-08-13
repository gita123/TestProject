@ui @sponsor
Feature: Sponsor Application Login Homepage
  This feature verifies the functionality on Sponsor App Login Homepage

  Scenario: Register to become a Sponsor

    When I open Sponsor App Homepage
    Then I click on the registration link
    And Enter registration name and email details
    And Confirm confirmation of registration


  @test1
  Scenario: Register to become a Sponsor
    When I open Sponsor App Homepage
    Then I click on the registration link
    And Enter registration name and email details
    And read userID value
    And Confirm confirmation of registration
#    And login to Sponsor app
    And read password from registered email and login to sponsor portal
    And apply for sponsor licence
#    And I am on metastorm page


  @test12
  Scenario: Login to Metastorm
#    And apply for sponsor licence
    Given I am on metastorm page
