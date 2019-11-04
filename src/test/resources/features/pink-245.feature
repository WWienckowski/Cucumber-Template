@PINK-245 @ShoppingBag @Sprint7 @Confirmed
Feature: Remove Item from Bag (Integration)
  User must be able to remove products from their shopping bag,
  both from the shopping bag page and in the mini-shopping bag.
	
  Scenario: Remove Item from bag with multiple items
		Given there are products in the Shopping Bag
		And the user is on the bag page 
		When the user clicks on the 'Remove Item' link in the bag
		Then the entire selected quantity of that product is removed from the cart
		And the product is no longer displayed in the shopping bag
		And the Shopping Bag Order Summary values are updated
		
	Scenario: Remove Item from bag with one item
		Given the user has a shirt in the bag
		And the user is on the bag page
		When the user clicks on 'Remove Item' link
		Then the empty shopping bag page is displayed
		
	Scenario: Remove Item from mini bag with multiple items
		Given there are products in the Shopping Bag
		And the user is on the home page
		And the mini shopping bag is expanded
		When the user clicks on the 'Remove Item' link in the mini bag
		Then the entire selected quantity of that product is removed from the cart
		And the product is no longer displayed in the mini shopping bag
		
	Scenario:	Remove Item from mini bag with one item
		Given the user has a shirt in the bag
		And the user is on the home page
		And the mini shopping bag is expanded
		When the user clicks on 'Remove Item' link
		Then the mini shopping bag is minimised