Feature: Testing a pushad settings 
  Users should be able to settings, Add the new users, Change datasource and Exclude lifestyle 

  Scenario: Users admin should be able add the user successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click add user
        And user provide text for search user "wad"
        And user click search user
        And user click first list user
        And user click select user type "user"
        And user click save user
    Then user should be see alert message "Success"
        And user should be see new user in page user management list
        And user click delete user data test
        And user click logout

  Scenario: Users admin should be able edit role the user successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click add user
        And user provide text for search user "wad"
        And user click search user
        And user click first list user
        And user click select user type "user"
        And user click save user
        And user should be see alert message "Success"
        And user click edit user and save
    Then user should be see new user in page user management list
        And user should be see new role "Admin"
        And user click delete user data test
        And user click logout   
        
  Scenario: Users admin should be able add the user admin successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click add user
        And user provide text for search user "wad"
        And user click search user
        And user click first list user
        And user click select user type "admin"
        And user click save user
    Then user should be see alert message "Success"
        And user should be see new user in page user management list
        And user click delete user data test
        And user click logout    
        
  Scenario: Users admin should be see message userName is already taken in case existing user 
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click add user
        And user provide text for search user "xxxx"
        And user click search user
        And user click list user
        And user click select user type "user"
        And user click save user
    Then user should be see alert message "userName is already taken"
        And user click logout 
        
  Scenario: Users admin should be change password datasource successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting
        And user click setting list choose change password datasource
        And user set data source password "J87654321"
        And user click save change password datasource
    Then user should be see alert message "Success"
        And user click logout 
        
  Scenario: Users admin should be change password datasource fail 
    Given user login pushad with "xxxx", "yyyy"
    When user click setting
        And user click setting list choose change password datasource
        And user set data source password ""
        And user click save change password datasource
    Then user should be see alert message "Set Data Source Password is required!"
        And user click logout               
  
    Scenario: Users admin should be see able add lifestyle exclude successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click setting list choose lifestyle exclude
        And user choose lifestyle name "Abortion", "Advertising", "Adware_Spyware"
        And user click save lifestyle
    Then user should be see alert message "Success"
        And user click setting list choose lifestyle exclude
        And user should be see lifestyle name "Abortion", "Advertising", "Adware_Spyware" selected 
        And user click logout    
        
   Scenario: Users admin should be see able remove some lifestyle exclude successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click setting list choose lifestyle exclude
        And user choose lifestyle name "Abortion"
        And user click save lifestyle
    Then user should be see alert message "Success"
        And user click setting list choose lifestyle exclude
        And user should be see lifestyle name "Advertising", "Adware_Spyware" selected
        And user click logout    
        
   Scenario: Users admin should be see able remove all lifestyle exclude successful
    Given user login pushad with "xxxx", "yyyy"
    When user click setting 
        And user click setting list choose lifestyle exclude
        And user choose lifestyle name "Advertising", "Adware_Spyware" 
        And user click save lifestyle
    Then user should be see alert message "Success"
        And user click setting list choose lifestyle exclude
        And user should be not see lifestyle name "Advertising", "Adware_Spyware" selected
        And user click logout            
     
        
        
            