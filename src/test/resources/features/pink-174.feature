@PINK-174 @CheckoutUI
Feature: Helpline Message (UI)
  The placement of the Pink helpline number message on desktop and mobile

  Background: 
  	Given the user is on the checkout page
  
  Scenario: The helpline message appears on desktop
		Then the 'Need Help?' message and '0-800-130-0060' are displayed below the 'Order Summary' as per designs 
	
	Scenario: The phone number is a clickable link
		Then the phone number is a clickable link
			| 1 |
		And the helpline link changes the mouse cursor to a pointer
	
	@mobile
	Scenario: The helpline message appears on mobile
		Then the 'Need Help?' message and '0-800-130-0060' are displayed below the 'Review' as per designs
	
	@mobile
	Scenario: The phone number is a clickable link on mobile
		Then the phone number is a clickable link
			| 0 |
		
	Scenario:	The helpline message remains beneath the Order Summary
		Given the Shopping Bag control is minimised
		When the user clicks on 'Shopping Bag'
		Then the helpline message is pushed down the page immediately beneath the Order Summary component

