@PINK-201 @Checkout @Sprint7 @incomplete
Feature: Create Loqate Service for UK Predictive Address Lookup
  Set up Loqate service to enable users to search and choose their preferred delivery address.

  Background:
  	Given the user has a shirt in the bag
  	And the user is on the checkout page
  
  Scenario: The predictive address will not suggest an address if less than 4 characters have been entered
    Given the user enters 3 characters in the checkout address search field
    Then no address suggestions will display

  Scenario: The predictive address suggests an address after entering 4 characters
    Given the user enters 4 characters in the checkout address search field
    Then the site will suggest addresses based on the user's entry

  Scenario: Selecting a suggested address expands and populates the address fields
    Given the user enters 4 characters in the checkout address search field
    When the user selects an address
    Then the delivery address fields are expanded
      | Address 1* |
      | City/Town* |
      | County*    |
      | Postcode*  |
    And each field is populated with the selected address value
      | Address 1* |
      | City/Town* |
      | County*    |
      | Postcode*  |
