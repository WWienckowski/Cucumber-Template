@PINK-124 @CheckoutUI
Feature: Interact with Delivery Options (UI)
  Users must be able to change their preferred delivery method based on the options presented to them in checkout.
  Each option will have different fields presented under

	Background:
		Given the user is on the checkout page
		

  Scenario: The user is presented with delivery options and Ship to Address is the default
	Then the user is presented with delivery options
		| Ship to Address          |              
		| Collection Point Pick Up | 
		| Collect in a Pink Store  | 
	And the user is in the Ship to Address section of the checkout page               
   
	Scenario Outline: The user selects an inactive delivery option
	When the user selects a <delivery option> that is not already selected
	Then that <delivery option> should be highlighted in black as per designs
	
	Examples:
		| delivery option |
		| 'Collection Point Pick Up' | 
		| 'Collect in a Pink Store'  |
	
	Scenario Outline: The user selects an active delivery option
	When the user selects a <delivery option> that is already selected
	Then that <delivery option> should remain active
	
	Examples:
		| delivery option |
		| 'Ship to Address'          |
		| 'Collection Point Pick Up' | 
		| 'Collect in a Pink Store'  |
