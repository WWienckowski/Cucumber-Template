@PINK-289 @Checkout @Sprint8 @incomplete
Feature: Delivery Speed Control (Integration)
  As a user, 
  I want to view what delivery options are available and how much they cost,
  So that I can choose the delivery option that meets my needs

  Scenario: Title of your scenario
    Given the user is on the <country> site
    When the user lands on the Checkout Page
    Then the 'Ship to Address' delivery option is selected
    And the Delivery Speed control contains the Delivery Speeds options set for the product in Commercetools
    And the price options are in <currency>

  Scenario: 
    Given that the user is on the 'Ship to Address' delivery option
    And the user is on the <country> site
    When the user selects a 'delivery speed' that is not the pre-selected option
    Then the radio button should be selected next to that option
    And the shipping on the 'order summary' section should update with the new price
    And the total should also update to include the new price
    And the 'shopping bag' above the 'order summary' should also reflect the new total
      | country | currency |
      | UK      | £        |
      | US      | $        |
