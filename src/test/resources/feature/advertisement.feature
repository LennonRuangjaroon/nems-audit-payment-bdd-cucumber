Feature: Testing a pushad Advertisement
  Users should be able Advertisement
  
  Scenario: Users should be able create advertisement and export successful
    Given user login pushad with "Test1", "123456"
        And user on the create advertisement setup page
        And user input data in form advertisement setup page "BDD"
        And user click save job
        And user should be see page advertisement setup
        And user should be see job name "BDD" in page advertisement setup list
    When user click advertisement list
    Then user should be see job name "BDD" in "" page advertisement list 
        And user click export job name "BDD#1"
        And user on page advertisement setup
        And user click delete job name "BDD"
        And user click logout

  Scenario: Users should be able create advertisement and re-run successful
    Given user login pushad with "Test1", "123456"
        And user on the create advertisement setup page
        And user input data in form advertisement setup page "BDD"
        And user click save job
        And user should be see page advertisement setup
        And user should be see job name "BDD" in page advertisement setup list
    When user click advertisement list
    Then user should be see job name "BDD" in "" page advertisement list 
        And user click re run job name "BDD#1"
        And user click execute Job
        #And user click re run job name "BDD#2"
        And user should be see job name "BDD#2" in "rerun" page advertisement list
        And user on page advertisement setup
        And user click delete job name "BDD"
        And user click logout

  
  
  
    