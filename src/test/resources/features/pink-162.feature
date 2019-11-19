@PINK-162 @Checkout @Sprint8 @incomplete
Feature: Order Summary - Shipping (Integration)
  As a user,
  I want to see what my shipping fees are associated with my order,
  So I can see how much my chosen fulfilment method is going to cost me

  Scenario: The Checkout page's Shipping summary shows the pre-selected delivery speed
    Given the user is navigating to the Checkout page
    When the user lands on the Delivery section
    Then the SHIPPING field in the Order Summary will be the value of the pre-selected Delivery Speed

  Scenario: The Checkout page's Shipping summary updates when delivery speed changes
    Given the user is on the Checkout page
    When the user changes the selection in the Delivery Speed component
    Then the SHIPPING field is updated to be the cost of the selected Delivery Speed

  Scenario: The Checkout page's Shipping summary will be zero if the user is Picking up in Store
    Given the user is on the Checkout page
    When the user selects 'Pick Up in a Pink Store'
    Then the value in the SHIPPING field is zero

  Scenario: The Checkout page's Shipping summary will update when the user switches from pick up to delivery
    Given the user is on the Delivery section
    And the user has selected 'Pick Up in a Pink Store'
    When the user selects 'Deliver to Address'
    Then the SHIPPING field is updated to the value of the pre-selected delivery speed

  Scenario: The Checkout page's shipping summary will display in the correct currency
    Given the user is navigating to the Checkout page
    And the user is on <country> site
    When the Checkout page loads
    Then the SHIPPING will be displayed in <currency>
    | £ | $ |                              