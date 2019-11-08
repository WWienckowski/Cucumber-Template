@PINK-129 @Checkout @Sprint3
Feature: Contact for Order Component (UI)
  This is the UI story for the 'Contact for Order' component on the Checkout page.
  This component is part of all the fulfilment options,
  but is slightly different depending on which fulfilment method is selected

  Background:
  	Given the user is on the checkout page
  	
  Scenario Outline: The 'Contact for Order' section of <delivery type> has the correct fields
		When the user clicks on the <delivery type> section
		Then there will be a 'Contact for order' component
		And the page will display all fields for <delivery type>
		And Contact for Order will contain an interactive checkbox that the user can check
   
   Examples:
   | delivery type |
   | 'Ship to Address' |
   | 'Collection Point Pick Up' | 
   | 'Collect in a Pink Store' |              
      

	Scenario: The checkbox for 'Contact for Order' is functional
		And the checkbox for 'Send me updates from Pink Shirtmaker' is not checked
		When the user clicks the checkbox for 'Send me updates from Pink Shirtmaker'
		Then the checkbox for 'Send me updates from Pink Shirtmaker' is checked
		When the user clicks the checkbox for 'Send me updates from Pink Shirtmaker'
		Then the checkbox for 'Send me updates from Pink Shirtmaker' is not checked