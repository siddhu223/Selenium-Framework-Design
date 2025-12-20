@Tag
Feature: Purchase the order from Ecommerce website
  # we can give here description about the feature
  
  Background:
  Given: I landed on Ecommerce Page

  @Tag2
  Scenario Outline: Positive Test of submitting the orderr
    Given Logged in with username <name> and password <password>
    When Add the product <productname> to cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER."  message is displayed in confirmation cart 
    Then shipping information

    Examples:
      | name                               | password       |  productname     |
      | siddheshmalusare0113@gmail.com     | Mal_sidh@0113  | ADIDIAS ORIGINAL |
      | malusarexyz@gmail.com              | Avinash@0113   |   ZARA COAT 3    |
       



