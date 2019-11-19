@PINK-336 @Checkout @Sprint8 @incomplete
Feature: Button Behaviour in Checkout
  As a system
  I want it to be clear when a user can interact with a button in Checkout
  So that checkout is easy and intuitive for them

  Background
    Given the user has a shirt in the bag
    And the user is on the checkout page

  Scenario: The continue button becomes active once the user has completed the 'Collect in a Pink Store' section
    Given the user is in the Collect in a Pink Store section
    When the user has provided valid entries for all required fields in the 'Collect in a Pink Store' section
    Then the 'Continue' button is active

  Scenario: The continue button becomes active once the user has completed the 'Ship to Address' section
    Given the user is in the Ship to Address section
    When the user has provided valid entries for all required fields in the 'Ship to Address' section
    Then the 'Continue' button is active

  Scenario: The Delivery section's active 'Continue' button leads the user to the Payment section
    Given the user is in the Ship to Address section
    And the user has provided valid entries for all required fields in the 'Ship to Address' section
    When the user clicks the 'Continue' button
    Then the page anchors back to the top of the page
    And the Payment section is active

  Scenario: The continue button becomes active once the user has completed the Credit Card Payment section
    Given the user is in the Card payment section
    When the user has provided valid credit card information
    Then the 'Continue' button is active

  Scenario: The continue button becomes active once the user has completed the Gift Card Payment section
    Given the user is in the Gift Card payment section
    When the user has successfully provided all required Gift Card information
    Then the 'Continue' button is active

  Scenario: The Payment section's active 'Continue' button leads the user to the Review section
    Given the 'Continue' button is active in the Payment section of Checkout
    When the user clicks the 'Continue' button
    Then the page is anchored to the top of the page
    And the Review section is active
    And the 'Place Order' button is inactive

  Scenario: The 'Place Order' button becomes active once the user has completed the Review section
    Given the Review section is active
    When the user checks the T&Cs box
    Then the 'Place Order' button is active