#Author: laferrierevirgo.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template


 Feature: TEST Project in JRI Application
    Scenario: Create JRI Account
     Given Invoke JRI Home Page
     And Verify the Create New Account page will display
     And Verify the User Name error message
     And Verify the Mobile error message
     And Verify the Email error message
     And Verify the Password error message
     And Verify the Term error message
     And Check the Refresh feature
     And Verify the Invalid data validation messages 
     Then Check user will able to Create an account with valid data
   
   Scenario: Login and Logout to JRI web Application 
     Given Invoke JRI Home Page
     And Navigate to Sing in page
     And Check the Validation Message
     Then Verify user will be able to login with valid data
     And Check the logout feature
     And Verify the Forgot password feature
     And Check the Web Elements on Forgot Password Page
     And Check the Validation Messages
     Then Verify email Forgot your password feature
   
    
    