@PINK-167 @Checkout @Sprint8 @incomplete
Feature: Order Summary - Total (Integration)
  As a user,
  I want to be able to see the total amount I owe for my items,
  So that I know the final amount I need to pay to complete checkout
  and receive my chosen products via my selected fulfilment method

  Scenario: The checkout's Total summary is the sum of all values in the order summary
    Given the user is navigating to the Checkout page
    And the user is on desktop
    When the user lands on the Checkout page
    Then the TOTAL field in the Order Summary will be the sum of all the other values in the Order Summary component

  Scenario: The checkout's Total summary is displayed in the correct currency
    Given the user is navigating to the Checkout page
    And the user is on desktop
    And the user is on <country> site
    When the Checkout page loads
    Then the TOTAL will be displayed in <currency>

  @mobile
  Scenario: The checkout's Total summary displays correctly on mobile
    Given the user is on the Checkout page
    And the user is on mobile
    When the user expands the Shopping Bag summary
    Then the TOTAL field in the Order Summary will be the sum of all the other values in the Order Summary component

  @mobile
  Scenario: The checkout's Total summary is displayed in the correct currency on mobile
    Given the user is on the Checkout page
    And the user is on mobile
    And the user is on <country> site
    When the user expands the Shopping Bag summary
    Then the TOTAL will be displayed in <currency>
    | country | currency |
    | UK      | US       |
    | £       | $        |

  Scenario: The checkout's Total summary reflects changes to other values in the order summary
    Given the user is on the Checkout page
    When the value of <value> changes
    Then the TOTAL field in the Order Summary will be the sum of all the other values in the Order Summary component
     | value |
     | SUBTOTAL |
     | SHIPPING |
     | TAX      |
     | VOUCHER  |
     | GIFT CARD |
  
   