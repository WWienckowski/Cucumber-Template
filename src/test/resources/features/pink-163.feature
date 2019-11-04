@PINK-163 @Checkout @Sprint7 @Confirmed
Feature: Order Summary - Subtotal (Integration)
  The SUBTOTAL field in the Order Summary must reflect the value of all products in the user's Shopping Bag.
	
	Background:
		Given there are products in the Shopping Bag
	
	Scenario: The checkout subtotal displays correctly on desktop
		Given the user is on the checkout page
		Then the SUBTOTAL field in the Order Summary will be the value of all the products in the user's Shopping Bag
		| desktop |
	@mobile
	Scenario:	The checkout subtotal displays correctly on mobile
		Given the user is on the checkout page
		When the user expands the Shopping Bag summary
		Then the SUBTOTAL field in the Order Summary will be the value of all the products in the user's Shopping Bag
		| mobile |