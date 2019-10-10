@PINK-230 @ShoppingBagUI @ShoppingBagServices
Feature: Quantity Selector on Shopping Bag (UI & Integration)
  As a user, 
	I want to be able to change the quantity of my items in my shopping bag, 
	so that I can buy more or less of the items I have chosen

	Background: 
		# 1 item of quantity 2, 1 item with quantity 1
		Given I put a cart into local storage 
		And the user is on the bag page

  Scenario Outline: The user's cart reflects changes made by increasing the quantity selector 
		Given the user is in <country> locale
		When the user increases the quantity using the quantity selector
		Then the user's cart is updated to include the increased quantity
		And the Order Summary fields are updated to reflect the change in cart quantity
		| <fields> |
		Examples:
		| country | fields |
		| US | Subtotal, Est. Tax, Est. Total |
		| UK | Subtotal, Total |
		| ROW | Subtotal, Est. Tax, Est. Total |
		
	Scenario Outline: The user's cart reflects changes made by decreasing the quantity selector 
		Given the user is in <country> locale
		When the user decreases the quantity using the quantity selector
		Then the user's cart is updated to include the decreased quantity
		And the Order Summary fields are updated to reflect the change in cart quantity
		| <fields> |
		Examples:
		| country | fields |
		| US | Subtotal, Est. Tax, Est. Total |
		| UK | Subtotal, Total |
		| ROW | Subtotal, Est. Tax, Est. Total |
		
		Scenario: The quantity selector can't decrement below 1
		Given the user has a quantity of 1 selected for a product
		When the user clicks '-' on the quantity selector
		Then the product quantity will remain 1


