Feature: Testing a pushad login 
  Users should be able to login pushad

  Scenario: Users should be able login pushad successful
        Given user on the login page
        When user provide the username "username" 
            And user provide the password "password"
            And user click login
        Then user should be see page advertisement setup
            And user click logout
    
  Scenario: Users login pushad failed with username session still active
    An active session for this user already exists
        Given user on the login page
        When user provide the username "username" 
            And user provide the password "password"
            And user click login
        Then user should be see page advertisement setup
            And user open new the login page
            And user provide the username "username" in the new login page
            And user provide the password "password" in the new login page
            And user click login at new the login page
            And user should be see message "An active session for this user already exists"
            And user click logout
            
  Scenario: Users login pushad failed with username or password wrong
        Given user on the login page
        When user provide the username "userX" 
            And user provide the password "passX"
            And user click login
        Then user should be see page advertisement login
        

