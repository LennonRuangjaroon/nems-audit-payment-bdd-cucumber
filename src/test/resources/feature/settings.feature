Feature: Testing a pushad settings 
  Users should be able to settings, Add the new users, Change password datasource and Exclude lifestyle 

  Scenario: Users admin should be able add the user successful
    Given user login pushad with "userrname", "password"
    When user click setting 
        And user click add user
        And user click search user
    Then user should be see page advertisement setup
        And user should be see job name "BDD" in page advertisement setup list
        And user click logout