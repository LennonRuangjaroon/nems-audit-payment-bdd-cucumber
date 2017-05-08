Feature: Testing the AuditPayment module
  Users should be able save data audit payment

  Scenario: Insert data with fill all fields should be successfully.
    Given User is on the Payment page
    When User input data field Reference Number Payment with "000001"
    And User input data field Date of payment with "01/03/2017"
    And User input data field Payment status with "ชำระเรียบร้อย"
    And User input data field Amount with "300"
    And User input data field Payee with "Admin Test"
    And User click save button to insert data
    Then User should see record the data successfully and then display the data in the table.
