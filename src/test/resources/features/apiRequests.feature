@api
Feature: Make a new cart, add an item


  Scenario: Make a post request
    Given the user is on the home page
    When I make a cart
    Then The pink-shopper should have a cart
    And I can add items to the cart
    
   Scenario: Add items manually
   	Given the user goes to a product page
   	And adds an item to the card
   	Then there will be an item in the cart 


