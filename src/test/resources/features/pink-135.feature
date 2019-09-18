@PINK-135 @CheckoutUI
Feature: Checkout Header (UI)
  Users will be provided with a simplified header when in the checkout

	Background:
		Given the user is on the checkout page

#  This may not be necessary or could be expanded upon

#  Scenario: The header appears correctly
#		Then the header should appear as per designs
	
	Scenario: The user clicks the Pink Shirtmaker logo in the header
		When the user clicks the Pink Shirtmaker logo in the header
		Then the user is re-directed to the Homepage
	
	Scenario: The user clicks the 'continue shopping' link in the header
		When the user clicks the 'continue shopping' link in the header
		Then the user exits checkout and is re-directed to the Bag
