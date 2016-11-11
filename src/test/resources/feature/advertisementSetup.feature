Feature: Testing a pushad Create Update Delete Advertisement Setup
  Users should be able Create Advertisement Setup

  Scenario: Users should be able create advertisement setup successful
        Given user login pushad with "username", "password"
            And user on the create advertisement setup page
            And user input data in form advertisement setup page "BDD"
        When user click save job
        Then user should be see page advertisement setup
            And user should be see job name "BDD" in page advertisement setup list
            And user click logout
            
  Scenario: Users should be able update advertisement setup successful
        Given user login pushad with "username", "password"
            And user should be see job name "BDD" in page advertisement setup list
        When user click edit
            And user update field name "Update"
            And user click delete life style
            And user click save job
        Then user should be see job name "BDDUpdate" in page advertisement setup list 
            And user click logout
 
  Scenario: Users should be able pause job successful
        Given user login pushad with "username", "password"
            And user should be see job name "BDDUpdate" in page advertisement setup list
        When user click pause job name "BDDUpdate"
        Then user should be see job name "BDDUpdate" pause status in page advertisement setup list 
            And user click logout  
            
  Scenario: Users should be able resume job successful
        Given user login pushad with "username", "password"
            And user should be see job name "BDDUpdate" in page advertisement setup list
        When user click resume job name "BDDUpdate"
        Then user should be see job name "BDDUpdate" resume status in page advertisement setup list 
            And user click logout                          
            
  Scenario: Users should be able delete advertisement setup successful
        Given user login pushad with "username", "password"
            And user should be see job name "BDDUpdate" in page advertisement setup list
        When user click delete job name "BDDUpdate"
        Then user should be not see job name "BDDUpdate" in page advertisement setup list 
            And user click logout            
             
  
  
  
  
    