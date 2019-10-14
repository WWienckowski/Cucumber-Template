@PINK-161 @CheckoutServices
Feature: View Shopping Bag from Checkout (Services)
  The user must be able to review what products are in their shopping bag, 
  attributes for each product and any gift messaging, all without leaving the Checkout page

	Background:
		# The user has items in the shopping bag, at least one with gift message and one without

  Scenario: Title of your scenario
    Given the user is in the Checkout page
		And the Shopping Bag summary component is not expanded
		When the expands the Shopping Bag summary
		Then the user's products are displayed as per designs
		And the primary image for each product is displayed
		And each applicable chosen attribute is displayed for each product
		| Commerce Tools attribute name |
		| Colour |
		| Fit |
		| Sleeve |
		| Size |
		| Collar Size |
		
	Scenario:	
		Given the user is on the Checkout page
		And the user has added Gift Wrap services to a product
		When the user expands the Shopping Bag summary
		Then the Gift Wrap box is displayed
		And the Gift Wrap box is checked and greyed out
		
	Scenario:	
		Given the user is on the Checkout page
		And the user has added Gift Wrap services to a product
		And the user has added a gift message to the product
		When the user expands the Shopping Bag summary
		Then the gift message field is displayed with the gift message
		And the gift message field is greyed out
		
	Scenario:	
		Given the user is on the Checkout page
		And the user has not added Gift Wrap services to a product
		When the user expands the Shopping Bag summary
		Then the Gift Wrap check box is not displayed
		And the gift message field is not displayed
	
	Scenario:	
		Given the user is on the Checkout page
		And the Shopping Bag summary is expanded
		When the user clicks 'Edit Shopping Bag'
		Then the user is taken to the Shopping Bag page
