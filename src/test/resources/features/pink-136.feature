@PINK-136 @CheckoutUI @Sprint4
Feature: Checkout Footer (UI)
  Users will be provided with a simplified footer when in the checkout
  
  Scenario: The checkout footer is simplified
  	Given there are products in the Shopping Bag
    And the user is on the checkout page
		Then the footer should appear as per designs
		And none of the footer icons are clickable
