@api 
Feature: API Test

  Scenario: Test we can hit Metastorm endpoint with a SOAP request for Sponsor App Creation
    Given the "pbs.metastormAPI.endpoint" endpoint is available
    Then I send SOAP request

