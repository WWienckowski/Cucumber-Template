@PINK-267 @ShoppingBag @Sprint6
Feature: Remove Item from Bag (Integration)
  User must be able to remove products from their shopping bag,
  both from the shopping bag page and in the mini-shopping bag.

  Scenario: Remove Item from bag with multiple items
		Given the user has more than one item in the bag
		And the user is on the bag page 
		When the user clicks 'Remove Item' in a product cell
		Then the entire selected quantity of that product is removed from the cart
		And the selected product cell disappears
		And the Order Summary values are updated
			| fields |
		
	Scenario: Remove Item from bag with one item
		Given the user has only one product in their shopping bag
		And the user is on the bag page
		When the user clicks 'Remove Item'
		Then the empty shopping bag page is displayed
		
	Scenario: Remove Item from mini bag with multiple items
		Given the user has more than one item in the bag
		And the user is on the home page
		And the user has expanded the mini shopping bag
		When the user clicks the 'Remove Item' button on a product line
		Then the entire selected quantity of that product is removed from the cart
		And the product is no longer displayed in the mini shopping bag
		
	Scenario:	Remove Item from mini bag with one item
		Given the user has only one product in their shopping bag
		And the user is on the home page
		And the mini shopping bag is expanded
		When the user clicks the 'Remove Item' button
		Then the mini shopping bag is minimised