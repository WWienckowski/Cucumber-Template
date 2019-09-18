@PINK-130 @CheckoutUI
Feature: Fulfilment summary messaging (UI)
  When the user has successfully complete the Delivery section
  then the site must present a summary component of the information that the user has entered or selected
	
	Background: 
		Given the user is on the checkout page
	
  Scenario Outline: The user sees a delivery summary after clicking continue
		And the user has completed all required information in the delivery component
		And the user has selected <delivery type>
		When the user clicks 'Continue'
		Then the delivery component will change to a summary component
		And the associated summary <fields> will display
		And there is a clickable 'Edit' link that returns the user to the delivery component 

	Examples:
		| delivery type | fields |
		| Ship to Address| DELIVERING TO, DELIVERING IN, CONTACT |
		| Collection Point Pick Up | PICKING UP, READY IN, CONTACT |
		| Collect in a Pink Store | PICKING UP, READY IN, CONTACT |                        

# Added this to the last step of the outline above

#GIVEN the site is showing the summary field 
#WHEN the user clicks the 'Edit' link
#THEN the summary section reverts to the delivery component
#AND the user can amend delivery information 
