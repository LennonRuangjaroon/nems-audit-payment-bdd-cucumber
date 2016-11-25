Feature: Testing a pushad Upload MSISDN 
  Users should be able Upload MSISDN, include list and exclude list

  Scenario: Users should be able Upload MSISDN include list
        Given user login pushad with "Test1", "123456"
            And user on the upload MSISDN page
        When user click select type "include" list
            And user click upload panel
            And user upload file type "include" 
        Then user should be see alert message "Upload success 5 records."
            And user click logout
            
  Scenario: Users should be able Upload MSISDN exclude list
        Given user login pushad with "Test1", "123456"
            And user on the upload MSISDN page
        When user click select type "exclude" list
            And user click upload panel
            And user upload file type "exclude"
        Then user should be see alert message "Upload success 5 records."
            And user click logout
            
            