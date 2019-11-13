@PINK-166 @Checkout @Sprint8 @incomplete
Feature: Order Summary - Tax (Integration)
  As a user,
  I want to see how much tax I am going to have to pay on my order,
  So I know how it will impact the total cost of my order

  Background:
    Given the user has a shirt in the bag
    And the user is on the checkout page

  Scenario: Tax will initially display as $0 (Desktop)
    Then the TAX field value on the checkout order summary will be zero
    And the checkout summary TAX will be displayed in dollars
    
  @mobile  
  Scenario: Tax will initially display as $0 (Mobile)
    When the user expands the Shopping Bag summary
    Then the TAX field value on the checkout order summary will be zero
    And the checkout summary TAX will be displayed in dollars
  
  Scenario: Tax will be calculated and displayed after the user enters a valid ZIP code
    And the user is in the Ship to Address section
    And the user has entered a valid ZIP code in the 'ZIP code' field
    | 23225 |
    When the user clicks out of the field
    | ZIP code |
    Then the system will calculate the tax to be applied to the order
    And the TAX field in the Order Summary will display the tax to be applied to the order