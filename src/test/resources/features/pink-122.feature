@PINK-122 @Checkout @Sprint3 @incomplete
Feature: Checkout text fields (UI)
  This story covers all free text fields in Checkout into which the user can enter information.
  The user must be able to know what to enter in each text field, 
  and to easily enter the field and add their information

	Background:
		Given the user is on the checkout page
	
	Scenario: All form elements have contextual placeholder text
	Then all text fields will contain ghost text to provide guidance for user input
	And all drop-down menu fields will contain ghost text directing user to make selection
	And the text will be grey as per designs
	And required fields will be represented with an asterisk next to the text as per designs

	Scenario: The user hovers over a text input field on desktop
	When the user hovers the cursor over a text field
	Then the cursor is a typing cursor icon

	Scenario: The user clicks into a text input field
	When the user clicks a field that does not yet contain user information
	Then the existing website text should disappear and the cell will contain only the typing cursor
	
	Scenario: The user enters text and it appears black
	Given the user is in a text field
	When the user types any information
	Then the text will be black as per designs
	
	Scenario: The user leaves a text field without entering anything
	Given the user is in the text field
	When the user clicks out of the text field without entering anything
	Then the information required text will show in the field
	
	Scenario: The user leaves a text field after typing, then returns to that field
	When the user clicks into a text field
	And the text field already has user-entered text in it
	Then the typing cursor will be where the user clicked
	And the user-entered text will remain

#come back to these
	#WHEN the user clicks on a drop down field
	#AND the drop down menu is minimised
	#THEN the drop down menu will expand and show the menu options
	
	#GIVEN the user is on the Checkout page
	#WHEN the user clicks on a drop down field
	#AND the drop down menu is expanded
	#THEN the drop down menu is minimised
	#AND the selected value is retained
