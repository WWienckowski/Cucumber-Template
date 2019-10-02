@PINK-123 @CheckoutUI
Feature: View Shopping Bag from Checkout (UI)
  When the user is on the checkout page they must be able to see a summary of what is in their shopping bag.
  The user cannot make changes to their bag from the checkout page,
  they must navigate to the shopping bag to make amendments to it.

  Background: 
    Given the user is on the checkout page

  Scenario: The user expands the Shopping Bag
    And the Shopping Bag control is minimised
    When the user clicks on 'Shopping Bag'
    Then the Shopping Bag control is expanded
    And the user can see the products in the Shopping Bag

  Scenario: The user minimises the Shopping Bag
    When the user clicks on 'Shopping Bag'
    And the Shopping Bag control is expanded
    When the user clicks on 'Shopping Bag'
    Then the Shopping Bag control is minimised

  Scenario: The user clicks the 'Edit Shopping Bag' link
    Given the user clicks on 'Shopping Bag'
    And the Shopping Bag control is expanded
    When the user clicks on 'Edit Shopping Bag' link
    Then the Shopping Bag page is loaded

  Scenario: Gift message is displayed
    When the user clicks on 'Shopping Bag'
    Then the gift message field is displayed underneath the associated product
    And the text is greyed out and not clickable

  @mobile
  Scenario: The mobile shopping bag header is sticky
    When the user scrolls down past the Shopping Bag header
    Then the Shopping Bag header is stuck to the top of the screen

  @mobile
  Scenario: The mobile shopping bag header returns to its original placement
    And the user scrolls down past the Shopping Bag header
    And the Shopping Bag header is stuck to the top of the screen
    When the user scrolls up past the original placement of the Shopping Bag header
    Then the Shopping Bag header is unstuck and in its original placement on the Checkout page
