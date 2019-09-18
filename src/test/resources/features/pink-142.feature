@PINK-142 @CheckoutUI
Feature: Payment Summary Messaging (UI)
  This is the UI story for the Payment summary section
  which will provide a non-interactive set of fields summarising the payment information supplied by the user.

  Background:
  	Given the user is on the checkout page
  	# There should be some steps in here to be able to continue to the review page
  
  Scenario: The user sees payment information in the Review section
		When the user continues to the Review section
		Then the Payment section will display a summary of the user's Payment information as per designs
		And there will be a clickable 'Edit' link
		And the Payment section will have grey text and be inactive

	Scenario: The user clicks the 'Edit' link next to the Payment section
		Given the user is in the Review section
		When the user clicks on the 'Edit' link in the Payment section
		Then the Payment fields will display and will be interactive
		And the Review field will become inactive
