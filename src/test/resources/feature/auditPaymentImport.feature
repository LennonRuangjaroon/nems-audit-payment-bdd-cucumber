Feature: Testing the AuditPayment Import module
  Users should be able Import data to audit payment

  Scenario: Insert data with fill all fields and attach valid data file Should be successfully
    Given User is on the Payment Import page
    When User input data field Year "2017"
    And User input data field exam type "EnglishTest(1)"
    And User Choose file for attach
    And User click import button
    Then User should be import data successfully

  Scenario: Insert data with fill all fields and Don't attach file Should not be successfully
    Given User is on the Payment Import page
    When User input data field Year "2017"
    And User input data field exam type "EnglishTest(1)"
    And User click import button
    Then User should be import data not be successfully
