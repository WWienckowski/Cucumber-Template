@PINK-251 @MiniShoppingBag @Sprint7 @Confirmed
Feature: Mini Shopping Bag (Integration)
  On all pages other than the shopping bag, when user is on desktop, 
  on hover of shopping bag icon display the mini bag as per designs. 

	The behaviour of the size changer and the quantity selector will be handled in separated tickets.

  Scenario: The mini shopping bag appears when items are in the cart
  	Given the user has a shirt in the bag
		When the user hovers over the Shopping Bag icon
		Then the mini shopping bag is expanded
		And the mini bag will display the item(s) in their bag
		
	Scenario:	The cart icon takes the user to the Shopping Bag page
		Given the user has a shirt in the bag
		When the user CLICKS on the shopping bag icon in the header
		Then the user is re-directed to the 'Shopping Bag'
		
	Scenario:	The mini shopping bag does not expand when the cart is empty
		Given the user does not have an item in their bag
		When the user hovers over the Shopping Bag icon
		Then the mini shopping bag is minimised
		