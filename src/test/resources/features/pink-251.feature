@PINK-251 @MiniShoppingBag @Sprint6
Feature: Mini Shopping Bag (Integration)
  On all pages other than the shopping bag, when user is on desktop, 
  on hover of shopping bag icon display the mini bag as per designs. 

	The behaviour of the size changer and the quantity selector will be handled in separated tickets.

  Scenario: The mini shopping bag appears when items are in the cart
  # handled in 123? 
  	Given the user has one or more items in their shopping bag
		And on any page that is not the shopping bag or the checkout
		When the user hovers over the shopping bag icon in the header
		Then the shopping bag will drop down as per designs
		And the user will be displayed the item(s) in their bag
		And no gift wrapping or gift message option will appear (differs to shopping bag page)
		
	Scenario:	The cart icon takes the user to the Shopping Bag page
		Given the user has a shirt in the bag
		When the user CLICKS on the shopping bag icon in the header
		Then the user is re-directed to the 'Shopping Bag'
		
	Scenario:	The mini shopping bag does not expand when the cart is empty
		Given the user does not have an item in their bag
		When the user hovers over the Shopping Bag icon
		Then the mini shopping bag is minimised
		