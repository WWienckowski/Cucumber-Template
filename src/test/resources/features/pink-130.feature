@PINK-130 @CheckoutUI
Feature: Fulfilment summary messaging (UI)
  When the user has successfully complete the Delivery section
  then the site must present a summary component of the information that the user has entered or selected
	
	Background: 
		Given the user is on the checkout page
	
  Scenario Outline: The user sees a delivery summary after clicking continue
		Given the user clicks on <delivery type>
		And the user has completed all required fields for <delivery type>
		When the user clicks on 'continue'
		Then the delivery component will change to a summary component
		And the associated summary fields will display for <delivery type> 

	Examples:
		| delivery type | fields |
		| 'Ship to Address' | DELIVERING TO, DELIVERING IN, CONTACT |
		| 'Collection Point Pick Up' | PICKING UP, READY IN, CONTACT |
		| 'Collect in a Pink Store' | PICKING UP, READY IN, CONTACT |                        
	
	Scenario: the user clicks the edit link in the summary field and is returned to the delivery component
		Given the site is showing the summary field 
		When the user clicks on 'Edit' link
		Then the summary section reverts to the delivery component
		And the user can amend delivery information 
