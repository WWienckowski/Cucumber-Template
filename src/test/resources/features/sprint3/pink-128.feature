@PINK-128 @Checkout @Sprint3
Feature: Delivery Speed Control (UI)
  This is the UI story for the delivery speed control in the 'Ship to Address' section of the checkout flow

  Background: 
    Given the user is on the checkout page
    And the Ship to Address section of the UK checkout page loads
	
	#Tag scenarios that have manual review elements?
	@ManualReview
  Scenario: Standard UK delivery is the default shipping option for delivery on the UK site
    Then the 'Standard UK Delivery' radio button should be selected
    # Ask about this, should I test this, include a screenshot?
    And the selected button will be a black ring surrounding a filled-in black circle

  Scenario: The user can select different shipping options
    When the user clicks on 'Next day UK Delivery'
    Then the 'Next day UK Delivery' radio button should be selected
