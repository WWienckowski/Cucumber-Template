@PINK-143 @CheckoutUI
Feature: Order Summary Component (UI)
  When the user is on desktop, there should be an order summary component on the 
  right hand side of the screen that specifies sub total of products in shopping bag,
  associated taxes and shipping fees, vouchers and gift cards if applicable and order total

  Background: 
    Given there are products in the Shopping Bag
    And the user is on the checkout page

  Scenario: The checkout page has an Order Summary
    Then there is an Order Summary component on the right hand side of the Checkout components underneath the Shopping Bag summary as per designs
    And the Order Summary component includes non-interactive fields

  @mobile
  Scenario: The checkout page has an Order Summary on mobile
    When the user clicks on 'Shopping Bag'
    Then there is an Order Summary component above the Edit Shopping Bag link
    And the mobile Order Summary component includes non-interactive fields

  @mobile
  Scenario: The mobile Order Summary is only visible when the shopping bag is expanded
    Given the user clicks on 'Shopping Bag'
    And the Shopping Bag control is expanded
    When the user clicks on 'Shopping Bag'
    And the Shopping Bag control is minimised
    Then the Order Summary is no longer displayed

  Scenario: The desktop Order Summary remains beneath the Shopping Bag when expanded
    And the Shopping Bag control is minimised
    When the user clicks on 'Shopping Bag'
    Then the Order Summary is pushed down the page immediately beneath the Shopping Bag summary

  Scenario: The desktop Order Summary remains beneath the Shopping Bag when minimised
    Given the user clicks on 'Shopping Bag'
    And the Shopping Bag control is expanded
    When the user clicks on 'Shopping Bag'
    Then the Order Summary returns to its original position immediately beneath the Shopping Bag summary
